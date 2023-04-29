package com.rodmel.best_travel.infraestructure.services;

import com.rodmel.best_travel.api.models.request.TourRequest;
import com.rodmel.best_travel.api.models.responses.TourResponse;
import com.rodmel.best_travel.domain.entities.jpa.*;
import com.rodmel.best_travel.domain.repositories.jpa.CustomerRepository;
import com.rodmel.best_travel.domain.repositories.jpa.FlyRepository;
import com.rodmel.best_travel.domain.repositories.jpa.HotelRepository;
import com.rodmel.best_travel.domain.repositories.jpa.TourRepository;
import com.rodmel.best_travel.infraestructure.abstract_services.ITourService;
import com.rodmel.best_travel.infraestructure.helpers.BlackListHelper;
import com.rodmel.best_travel.infraestructure.helpers.CustomerHelper;
import com.rodmel.best_travel.infraestructure.helpers.TourHelper;
import com.rodmel.best_travel.util.enums.Tables;
import com.rodmel.best_travel.util.exeptions.IdNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;
import java.util.stream.Collectors;

@Transactional
@Service
@Slf4j
@AllArgsConstructor
public class TourService implements ITourService {

    private final TourRepository tourRepository;
    private final FlyRepository flyRepository;
    private final HotelRepository hotelRepository;
    private final CustomerRepository customerRepository;

    private final TourHelper tourHelper;
    private final CustomerHelper customerHelper;
    private BlackListHelper blackListHelper;



    @Override
    public TourResponse create(TourRequest request) {
        blackListHelper.isInBlackListCustomer(request.getCustomerId());
        var customer = customerRepository.findById(request.getCustomerId()).orElseThrow(()->new IdNotFoundException(Tables.customer.name()));
        var flights = new HashSet<FlyEntity>();
        request.getFlights().forEach(fly->flights.add(this.flyRepository.findById(fly.getId()).orElseThrow(()->new IdNotFoundException(Tables.fly.name()))));
        var hotels = new HashMap<HotelEntity,Integer>();
        request.getHotels().forEach(hotel->hotels.put(this.hotelRepository.findById(hotel.getId()).orElseThrow(),hotel.getTotalDays()));

        var tourToSave = TourEntity.builder()
                .tickets(this.tourHelper.createTicket(flights, customer))
                .reservations(this.tourHelper.createReservations(hotels,customer))
                .customer(customer)
                .build();

        var tourSaved = this.tourRepository.save(tourToSave);
        //contador
        this.customerHelper.incrase(customer.getDni(), TourService.class);

        return TourResponse.builder()
                .reservationIds(tourSaved.getReservations().stream().map(ReservationEntity::getId).collect(Collectors.toSet()))
                .ticketIds(tourSaved.getTickets().stream().map(TicketEntity::getId).collect(Collectors.toSet()))
                .id(tourSaved.getId())
                .build();
    }

    @Override
    public TourResponse read(Long id) {
        var tourFromDb = this.tourRepository.findById(id).orElseThrow(()->new IdNotFoundException(Tables.tour.name()));

        return TourResponse.builder()
                .reservationIds(tourFromDb.getReservations().stream().map(ReservationEntity::getId).collect(Collectors.toSet()))
                .ticketIds(tourFromDb.getTickets().stream().map(TicketEntity::getId).collect(Collectors.toSet()))
                .id(tourFromDb.getId())
                .build();
    }

    @Override
    public void delete(Long id) {
        var tourToDelete = tourRepository.findById(id).orElseThrow();
        this.tourRepository.delete(tourToDelete);

    }

    @Override
    public void removeTicket(Long tourId,UUID ticketId) {
        var tourUpdate = this.tourRepository.findById(tourId).orElseThrow(()->new IdNotFoundException(Tables.tour.name()));
        tourUpdate.removeTicket(ticketId);
        this.tourRepository.save(tourUpdate);

    }

    @Override
    public UUID addTicket(Long flyId, Long tourId) {
        var tourUpdate = this.tourRepository.findById(tourId).orElseThrow(()->new IdNotFoundException(Tables.tour.name()));
        var fly = this.flyRepository.findById(flyId).orElseThrow();
        var ticket = this.tourHelper.createTicket(fly,tourUpdate.getCustomer());
        tourUpdate.addTicket(ticket);
        this.tourRepository.save(tourUpdate);

        return ticket.getId();
    }

    @Override
    public void removeReservation(Long tourId,UUID reservationId) {
        var tourUpdate = this.tourRepository.findById(tourId).orElseThrow(()->new IdNotFoundException(Tables.tour.name()));
        tourUpdate.removeReservation(reservationId);
        this.tourRepository.save(tourUpdate);

    }

    @Override
    public UUID addReservation(Long tourId, Long hotelId, Integer totalDays) {
        var tourUpdate = this.tourRepository.findById(tourId).orElseThrow(()->new IdNotFoundException(Tables.tour.name()));
        var hotel = this.hotelRepository.findById(hotelId).orElseThrow();
        var reservation = this.tourHelper.createReservation(hotel,tourUpdate.getCustomer(),totalDays);
        tourUpdate.addReservation(reservation);
        this.tourRepository.save(tourUpdate);

        return reservation.getId();
    }
}
