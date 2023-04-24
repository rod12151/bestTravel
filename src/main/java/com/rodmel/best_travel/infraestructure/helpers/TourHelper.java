package com.rodmel.best_travel.infraestructure.helpers;

import com.rodmel.best_travel.domain.repositories.ReservationRepository;
import com.rodmel.best_travel.domain.repositories.TicketRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Transactional
@Component
@AllArgsConstructor
public class TourHelper {
    private final TicketRepository ticketRepository;
    private final ReservationRepository reservationRepository;
}
