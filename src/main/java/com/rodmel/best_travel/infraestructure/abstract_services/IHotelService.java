package com.rodmel.best_travel.infraestructure.abstract_services;

import com.rodmel.best_travel.api.models.responses.HotelResponse;

import java.util.Set;

public interface IHotelService extends CatalogService<HotelResponse>{

    Set<HotelResponse> readByRating(Integer rating);
}
