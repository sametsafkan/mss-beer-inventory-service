package com.sametsafkan.beer.inventory.service.service;

import com.sametsafkan.mssbrewery.model.BeerOrderDto;

public interface DeallocationService {
    void deallocateOrder(BeerOrderDto beerOrderDto);
}
