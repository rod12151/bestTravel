package com.rodmel.best_travel.infraestructure.services;

import com.rodmel.best_travel.api.models.request.ReservationRequest;

import com.rodmel.best_travel.api.models.responses.HotelResponse;
import com.rodmel.best_travel.api.models.responses.ReservationResponse;

import com.rodmel.best_travel.domain.entities.jpa.ReservationEntity;

import com.rodmel.best_travel.domain.repositories.jpa.CustomerRepository;
import com.rodmel.best_travel.domain.repositories.jpa.HotelRepository;
import com.rodmel.best_travel.domain.repositories.jpa.ReservationRepository;
import com.rodmel.best_travel.infraestructure.abstract_services.IReservationService;
import com.rodmel.best_travel.infraestructure.helpers.BlackListHelper;
import com.rodmel.best_travel.infraestructure.helpers.CustomerHelper;
import com.rodmel.best_travel.util.exeptions.IdNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
@Transactional
@Service
@Slf4j
@AllArgsConstructor
public class ReservationService implements IReservationService {
    private final HotelRepository hotelRepository;
    private final CustomerRepository customerRepository;
    private final ReservationRepository reservationRepository;

    private final CustomerHelper customerHelper;

    private BlackListHelper blackListHelper;

    @Override
    public ReservationResponse create(ReservationRequest request) {
        blackListHelper.isInBlackListCustomer(request.getIdClient());
        var hotel = this.hotelRepository.findById(request.getIdHotel()).orElseThrow(()->new IdNotFoundException("hotel"));
        var customer = this.customerRepository.findById(request.getIdClient()).orElseThrow(()->new IdNotFoundException("customer"));
        var reservationToPersist = ReservationEntity.builder()
                .id(UUID.randomUUID())
                .hotel(hotel)
                .customer(customer)
                .totalDays(request.getTotalDays())
                .dateTimeReservation(LocalDateTime.now())
                .dateStart(LocalDate.now())
                .dateEnd(LocalDate.now().plusDays(request.getTotalDays()))
                .price(hotel.getPrice().add(hotel.getPrice().multiply(charges_price_percentage)))
                .build();
        var reservationPersisted = reservationRepository.save(reservationToPersist);
        this.customerHelper.incrase(customer.getDni(), ReservationService.class);
        return this.entityToResponse(reservationPersisted);
    }

    @Override
    public ReservationResponse read(UUID id) {
        var reservationFromBd = this.reservationRepository.findById(id).orElseThrow(()->new IdNotFoundException("reservation"));
        return this.entityToResponse(reservationFromBd);
    }

    @Override
    public ReservationResponse update(ReservationRequest request, UUID id) {
        var hotel = hotelRepository.findById(request.getIdHotel()).orElseThrow(()->new IdNotFoundException("hotel"));
        var reservationToUpdate = reservationRepository.findById(id).orElseThrow(()->new IdNotFoundException("reservation"));
        reservationToUpdate.setHotel(hotel);
        reservationToUpdate.setTotalDays(request.getTotalDays());
        reservationToUpdate.setDateTimeReservation(LocalDateTime.now());
        reservationToUpdate.setDateStart(LocalDate.now());
        reservationToUpdate.setDateEnd(LocalDate.now().plusDays(request.getTotalDays()));
        reservationToUpdate.setPrice(hotel.getPrice().add(hotel.getPrice().multiply(charges_price_percentage)));

        var reservationUpdated = this.reservationRepository.save(reservationToUpdate);
        log.info("Ticket update with id {}",reservationUpdated.getId());

        return this.entityToResponse(reservationUpdated);
    }

    @Override
    public void delete(UUID id) {
        var reservationToDelete = reservationRepository.findById(id).orElseThrow(()->new IdNotFoundException("reservation"));
        this.reservationRepository.delete(reservationToDelete);


    }
    @Override
    public BigDecimal findPrice(Long hotelId) {
        var hotel = this.hotelRepository.findById(hotelId).orElseThrow(()->new IdNotFoundException("hotel"));
        return hotel.getPrice().add(hotel.getPrice().multiply(charges_price_percentage));
    }
    private ReservationResponse entityToResponse(ReservationEntity entity){
        var response = new ReservationResponse();
        BeanUtils.copyProperties(entity,response);
        var hotelResponse = new HotelResponse();
        BeanUtils.copyProperties(entity.getHotel(),hotelResponse);
        response.setHotel(hotelResponse);
        return response;
    }
    public static final BigDecimal charges_price_percentage = BigDecimal.valueOf(0.20);
}
