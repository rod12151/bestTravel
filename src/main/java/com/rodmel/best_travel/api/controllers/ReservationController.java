package com.rodmel.best_travel.api.controllers;

import com.rodmel.best_travel.api.models.request.ReservationRequest;

import com.rodmel.best_travel.api.models.responses.ReservationResponse;

import com.rodmel.best_travel.infraestructure.abstract_services.IReservationService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(path = "reservation")
@AllArgsConstructor
public class ReservationController {
    private final IReservationService reservationService;
    @Operation(summary = "post  a reservation")
    @PostMapping
    public ResponseEntity<ReservationResponse> post(@Valid @RequestBody ReservationRequest request){
        return ResponseEntity.ok(reservationService.create(request));

    }
    @Operation(summary = "get  a reservation")
    @GetMapping(path = "{id}")
    public ResponseEntity<ReservationResponse> get(@PathVariable UUID id){
        return ResponseEntity.ok(reservationService.read(id));
    }
    @Operation(summary = "update  a reservation from id")
    @PutMapping(path = "{id}")
    public ResponseEntity<ReservationResponse> put(@Valid @PathVariable UUID id,@RequestBody ReservationRequest request){
        return ResponseEntity.ok(this.reservationService.update(request,id));
    }
    @Operation(summary = "delete  a reservation from id")
    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        this.reservationService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @Operation(summary = "get price reservation from id")
    @GetMapping
    public ResponseEntity<Map<String, BigDecimal>> getReservationPrice(@RequestParam long hotelId){
        return ResponseEntity.ok(Collections.singletonMap("reservationPrice",this.reservationService.findPrice(hotelId)));
}
}
