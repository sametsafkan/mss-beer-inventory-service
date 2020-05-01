package com.sametsafkan.beer.inventory.service.service;

import com.sametsafkan.brewery.model.BeerOrderDto;

public interface DeallocationService {
    void deallocateOrder(BeerOrderDto beerOrderDto);
}
