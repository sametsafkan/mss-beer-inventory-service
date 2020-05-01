package com.sametsafkan.beer.inventory.service.listener;

import com.sametsafkan.beer.inventory.service.config.JmsConfig;
import com.sametsafkan.beer.inventory.service.domain.BeerInventory;
import com.sametsafkan.brewery.model.BeerDto;
import com.sametsafkan.brewery.event.NewInventoryEvent;
import com.sametsafkan.beer.inventory.service.repositories.BeerInventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class NewBeerInventoryListener {

    private final BeerInventoryRepository beerInventoryRepository;

    @JmsListener(destination = JmsConfig.NEW_INVENTORY_QUEUE)
    public void listen(NewInventoryEvent event){
        BeerDto beerDto = event.getBeerDto();
        log.info("Beer inventory updated for " + beerDto.getId());
        beerInventoryRepository.save(BeerInventory.builder()
                .beerId(beerDto.getId())
                .upc(beerDto.getUpc())
                .quantityOnHand(beerDto.getQuantityOnHand())
                .build());
    }
}
