package com.rodmel.best_travel;

import com.rodmel.best_travel.domain.entities.ReservationEntity;
import com.rodmel.best_travel.domain.entities.TicketEntity;
import com.rodmel.best_travel.domain.entities.TourEntity;
import com.rodmel.best_travel.domain.repositories.CustomerRepository;
import com.rodmel.best_travel.domain.repositories.FlyRepository;
import com.rodmel.best_travel.domain.repositories.HotelRepository;
import com.rodmel.best_travel.domain.repositories.TourRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@SpringBootApplication
@Slf4j
public class BestTravelApplication{


	public static void main(String[] args) {SpringApplication.run(BestTravelApplication.class, args);}





}
