package com.sametsafkan.beer.inventory.service.listener;

import com.sametsafkan.beer.inventory.service.config.JmsConfig;
import com.sametsafkan.beer.inventory.service.service.AllocationService;
import com.sametsafkan.mssbrewery.event.AllocateOrderRequest;
import com.sametsafkan.mssbrewery.event.AllocateOrderResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AllocationRequestListener {

    private final JmsTemplate jmsTemplate;
    private final AllocationService allocationService;

    @JmsListener(destination = JmsConfig.ALLOCATE_ORDER)
    public void listen(AllocateOrderRequest request){
        AllocateOrderResult.AllocateOrderResultBuilder builder = AllocateOrderResult.builder().beerOrderDto(request.getBeerOrderDto());
        try {
            Boolean result = allocationService.allocateOrder(request.getBeerOrderDto());
            if(result)
                builder.pendingInventory(false);
            else
                builder.pendingInventory(true);
            builder.allocationError(false);
        }catch (Exception e){
            log.error("Allocation failed for order id : " + request.getBeerOrderDto().getId(), e);
            builder.allocationError(true);
        }
        jmsTemplate.convertAndSend(JmsConfig.ALLOCATE_ORDER_RESULT_QUEUE, AllocateOrderResult.builder().build());
    }
}
