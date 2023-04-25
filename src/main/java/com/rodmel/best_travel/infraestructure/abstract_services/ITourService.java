package com.rodmel.best_travel.infraestructure.abstract_services;

import com.rodmel.best_travel.api.models.request.TourRequest;
import com.rodmel.best_travel.api.models.responses.TourResponse;

import java.util.UUID;

public interface ITourService extends SimpleCrudService<TourRequest,TourResponse,Long>{

    void removeTicket(Long tourId,UUID ticketId);

    UUID addTicket(Long flyId, Long tourId);
    void removeReservation(Long tourId, UUID ReservationId);

    UUID addReservation(Long ReservationId, Long tourId, Integer totalDays);


}
