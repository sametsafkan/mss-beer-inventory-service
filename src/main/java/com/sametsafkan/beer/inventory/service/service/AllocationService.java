package com.sametsafkan.beer.inventory.service.service;

import com.sametsafkan.mssbrewery.model.BeerOrderDto;

public interface AllocationService {
    Boolean allocateOrder(BeerOrderDto beerOrderDto);

    void deallocateOrder(BeerOrderDto beerOrderDto);
}
