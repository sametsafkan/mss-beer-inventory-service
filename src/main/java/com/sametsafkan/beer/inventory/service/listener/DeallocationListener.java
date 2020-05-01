package com.sametsafkan.beer.inventory.service.listener;

import com.sametsafkan.beer.inventory.service.config.JmsConfig;
import com.sametsafkan.beer.inventory.service.service.DeallocationService;
import com.sametsafkan.mssbrewery.event.DeallocateOrderRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class DeallocationListener {

    private final DeallocationService allocationService;

    @JmsListener(destination = JmsConfig.DEALLOCATE_ORDER_QUEUE)
    public void listen(DeallocateOrderRequest request){
        allocationService.deallocateOrder(request.getBeerOrderDto());
    }
}
