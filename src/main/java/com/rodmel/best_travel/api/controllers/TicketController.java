package com.rodmel.best_travel.api.controllers;

import com.rodmel.best_travel.api.models.request.TicketRequest;
import com.rodmel.best_travel.api.models.responses.TicketResponse;
import com.rodmel.best_travel.infraestructure.abstract_services.ITicketService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(path = "ticket")
@AllArgsConstructor
public class TicketController {

    private final ITicketService ticketService;
    @Operation(summary = "post  a ticket")
    @PostMapping()
    public ResponseEntity<TicketResponse> post (@Valid  @RequestBody TicketRequest request){
        return ResponseEntity.ok(ticketService.create(request));
    }
    @Operation(summary = "get  a ticket")
    @GetMapping(path = "{id}")
    public ResponseEntity<TicketResponse> get(@PathVariable UUID id){
        return ResponseEntity.ok(ticketService.read(id));
    }
    @Operation(summary = "update  a ticket ")
    @PutMapping(path = "{id}")
    public ResponseEntity<TicketResponse> put(@PathVariable UUID id,@RequestBody TicketRequest request){
        return ResponseEntity.ok(this.ticketService.update(request,id));
    }
    @Operation(summary = "delete  a ticket from system")
    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        this.ticketService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @Operation(summary = "return price of one fly with id")
    @GetMapping
    public ResponseEntity<Map<String, BigDecimal>> getFlyPrice(@RequestParam long flyId){
        return ResponseEntity.ok(Collections.singletonMap("flyPrice",this.ticketService.findPrice(flyId)));

    }


}
