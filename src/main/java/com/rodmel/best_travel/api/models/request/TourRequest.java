package com.rodmel.best_travel.api.models.request;

import jakarta.validation.constraints.Size;
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
public class TourRequest implements Serializable {
    public String customerId;
    @Size(min = 1,message = "Min flight tour per tour")
    private Set<TourFlyRequest> flights;
    @Size(min = 1,message = "Min flight tour per tour")
    private Set<TourHotelRequest> hotels;
}
