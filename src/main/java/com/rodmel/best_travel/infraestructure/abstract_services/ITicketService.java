package com.rodmel.best_travel.infraestructure.abstract_services;

import com.rodmel.best_travel.api.models.request.TicketRequest;
import com.rodmel.best_travel.api.models.responses.TicketResponse;

import java.util.UUID;

public interface ITicketService extends CrudService<TicketRequest, TicketResponse, UUID>{
}
