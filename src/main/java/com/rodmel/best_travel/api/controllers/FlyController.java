package com.rodmel.best_travel.api.controllers;

import com.rodmel.best_travel.api.models.responses.FlyResponse;
import com.rodmel.best_travel.infraestructure.abstract_services.IFlyService;
import com.rodmel.best_travel.util.enums.SortType;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

@RestController
@RequestMapping(path = "fly")
@AllArgsConstructor
public class FlyController {
    private final IFlyService flyService;
    @Operation(summary = "get all flights paginated and ordered ")
    @GetMapping
    public ResponseEntity<Page<FlyResponse>> getAll(
            @RequestParam Integer page,
            @RequestParam Integer size,
            @RequestHeader(required = false) SortType sortType){
        if (Objects.isNull(sortType)) sortType =SortType.NONE;
        var response = this.flyService.realAll(page,size,sortType);

        return response.isEmpty()?ResponseEntity.noContent().build() : ResponseEntity.ok(response);

    }
    @Operation(summary = "get all flights less than a given price")
    @GetMapping(path = "less_price")
    public ResponseEntity<Set<FlyResponse>> getLessPrice(
            @RequestParam BigDecimal price){
        var response = this.flyService.readLessPrice(price);
        return response.isEmpty()? ResponseEntity.noContent().build(): ResponseEntity.ok(response);
    }
    @Operation(summary = "get all flights between one price ")
    @GetMapping(path = "between_price")
    public ResponseEntity<Set<FlyResponse>> getBetweenPrice(
            @RequestParam BigDecimal min,
            @RequestParam BigDecimal max){
        var response = this.flyService.readBetweenPrices(min,max);
        return response.isEmpty()? ResponseEntity.noContent().build(): ResponseEntity.ok(response);
    }
    @Operation(summary = "get all flights given an origin and destination")
    @GetMapping(path = "origin_destiny")
    public ResponseEntity<Set<FlyResponse>> getByOriginDestiny(
            @RequestParam String origin,
            @RequestParam String destiny){
        var response = this.flyService.readByOriginDestiny(origin,destiny);
        return response.isEmpty()? ResponseEntity.noContent().build(): ResponseEntity.ok(response);
    }

}
