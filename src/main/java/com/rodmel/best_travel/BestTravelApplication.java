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
public class BestTravelApplication implements CommandLineRunner {
	@Autowired
	private HotelRepository hotelRepository;
	@Autowired
	private FlyRepository flyRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private TourRepository tourRepository;


	public static void main(String[] args) {SpringApplication.run(BestTravelApplication.class, args);}

	@Override
	public void run(String... args) throws Exception {
		// var fly =flyRepository.findById(15L).get();
		//var hotel = hotelRepository.findById(5L).get();
		//log.info(String.valueOf(fly));
		//log.info(String.valueOf(hotel));
		//this.flyRepository.selectLessPrice(BigDecimal.valueOf(20)).forEach(f-> System.out.println(f));
		//this.flyRepository.selectBetweenPrice(BigDecimal.valueOf(10),BigDecimal.valueOf(15)).forEach(System.out::println);
		//this.flyRepository.selectOriginDestiny("Grecia","Mexico").forEach(System.out::println);
		//var fly = flyRepository.findById(1L).get();
		//System.out.println(fly);
		//fly.getTickets().forEach(ticket -> System.out.println(ticket));
		//var fly = flyRepository.findByTicketId(UUID.fromString("22345678-1234-5678-3235-567812345678"));
		//hotelRepository.findByPriceLessThan(BigDecimal.valueOf(100)).forEach(System.out::println);
		//hotelRepository.findByPriceIsBetween(BigDecimal.valueOf(50),BigDecimal.valueOf(100)).forEach(System.out::println);
		//hotelRepository.findByRatingGreaterThan(4).forEach(System.out::println);
		//var hotel= hotelRepository.findByReservationId(UUID.fromString("22345678-1234-5678-1234-567812345678"));
		var  customer=customerRepository.findByDni("BBMB771012HMCRR022").orElseThrow();
		log.info("client name: " + customer.getFullName());
		var fly = flyRepository.findById(11L).orElseThrow();
		log.info("fly: "+ fly.getOriginName()+ "-"+fly.getDestinyName());
		var hotel = hotelRepository.findById(3L).orElseThrow();
		log.info("hotel: "+ hotel.getName());
		var tour = TourEntity.builder()
				.customer(customer)
				.build();
		var ticket = TicketEntity.builder()
				.id(UUID.randomUUID())
				.price(fly.getPrice().multiply(BigDecimal.TEN))
				.arrivalDate(LocalDate.now())
				.departureDate(LocalDate.now())
				.customer(customer)
				.tour(tour)
				.fly(fly)
				.build();
		var reservation = ReservationEntity.builder()
				.id(UUID.randomUUID())
				.dateTimeReservation(LocalDateTime.now())
				.dateEnd(LocalDate.now().plusDays(2))
				.dateStart(LocalDate.now().plusDays(1))
				.hotel(hotel)
				.customer(customer)
				.tour(tour)
				.totalDays(1)
				.price(hotel.getPrice().multiply(BigDecimal.TEN))
				.build();
		System.out.println("-------SAVE-------");

		this.tourRepository.save(tour);




}}
