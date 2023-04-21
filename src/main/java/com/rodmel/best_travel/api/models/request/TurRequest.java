package com.rodmel.best_travel.api.models.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TurRequest implements Serializable {
    public Serializable customerId;
    private Set<TourFlyRequest> flights;
    private Set<TourHotelRequest> hotels;
}
