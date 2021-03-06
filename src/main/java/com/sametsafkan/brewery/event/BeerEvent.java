package com.sametsafkan.brewery.event;

import com.sametsafkan.brewery.model.BeerDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BeerEvent {

    private BeerDto beerDto;
}
