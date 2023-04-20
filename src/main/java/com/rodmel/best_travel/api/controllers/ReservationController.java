package com.rodmel.best_travel.api.controllers;

import com.rodmel.best_travel.api.models.request.ReservationRequest;
import com.rodmel.best_travel.api.models.responses.ReservationResponse;
import com.rodmel.best_travel.infraestructure.abstract_services.IReservationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "reservation")
@AllArgsConstructor
public class ReservationController {
    private final IReservationService reservationService;
    @PostMapping
    public ResponseEntity<ReservationResponse> post(@RequestBody ReservationRequest request){
        return ResponseEntity.ok(reservationService.create(request));

    }
}
