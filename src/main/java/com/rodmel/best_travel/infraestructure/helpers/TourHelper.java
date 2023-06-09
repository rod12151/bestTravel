package com.rodmel.best_travel.infraestructure.helpers;

import com.rodmel.best_travel.domain.entities.jpa.*;
import com.rodmel.best_travel.domain.repositories.jpa.ReservationRepository;
import com.rodmel.best_travel.domain.repositories.jpa.TicketRepository;
import com.rodmel.best_travel.infraestructure.services.ReservationService;
import com.rodmel.best_travel.infraestructure.services.TicketService;
import com.rodmel.best_travel.util.BestTravelUtil;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

//esto nos ayudara crear los tickets y reservaciones
@Transactional
@Component
@AllArgsConstructor
public class TourHelper {
    private final TicketRepository ticketRepository;
    private final ReservationRepository reservationRepository;

    public Set<TicketEntity> createTicket(Set<FlyEntity> flights, CustomerEntity customer) {
        var response = new HashSet<TicketEntity>(flights.size());
        flights.forEach(fly -> {
            var ticketToPersist = TicketEntity.builder()
                    .id(UUID.randomUUID())
                    .fly(fly)
                    .customer(customer)
                    .price(fly.getPrice().add(fly.getPrice().multiply(TicketService.charger_price_percentage)))
                    .purchaseDate(LocalDate.now())
                    .arrivalDate(BestTravelUtil.getRandomSoon())
                    .departureDate(BestTravelUtil.getRandomLatter())
                    .build();
            response.add(this.ticketRepository.save(ticketToPersist));

        });
        return response;
    }

    public Set<ReservationEntity> createReservations(HashMap<HotelEntity, Integer> hotels, CustomerEntity customer) {
        var response = new HashSet<ReservationEntity>(hotels.size());
        hotels.forEach((hotel, totalDays) -> {
            var reservationToPersist = ReservationEntity.builder()
                    .id(UUID.randomUUID())
                    .hotel(hotel)
                    .customer(customer)
                    .totalDays(totalDays)
                    .dateTimeReservation(LocalDateTime.now())
                    .dateStart(LocalDate.now())
                    .dateStart(LocalDate.now())
                    .dateEnd(LocalDate.now().plusDays(totalDays))
                    .price(hotel.getPrice().add(hotel.getPrice().multiply(ReservationService.charges_price_percentage)))
                    .build();
            response.add(this.reservationRepository.save(reservationToPersist));
        });
        return response;
    }

    public TicketEntity createTicket(FlyEntity fly, CustomerEntity customer) {
        var ticketToPersist = TicketEntity.builder()
                .id(UUID.randomUUID())
                .fly(fly)
                .customer(customer)
                .price(fly.getPrice().add(fly.getPrice().multiply(TicketService.charger_price_percentage)))
                .purchaseDate(LocalDate.now())
                .arrivalDate(BestTravelUtil.getRandomSoon())
                .departureDate(BestTravelUtil.getRandomLatter())
                .build();
        return this.ticketRepository.save(ticketToPersist);

    }

    public ReservationEntity createReservation(HotelEntity hotel, CustomerEntity customer, Integer totaldays) {
        var reservationToPersist = ReservationEntity.builder()
                .id(UUID.randomUUID())
                .hotel(hotel)
                .customer(customer)
                .totalDays(totaldays)
                .dateTimeReservation(LocalDateTime.now())
                .dateStart(LocalDate.now())
                .dateEnd(LocalDate.now().plusDays(totaldays))
                .price(hotel.getPrice().add(hotel.getPrice().multiply(ReservationService.charges_price_percentage)))
                .build();
        return this.reservationRepository.save(reservationToPersist);
    }


}


