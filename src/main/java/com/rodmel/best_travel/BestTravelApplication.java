package com.rodmel.best_travel;

import com.rodmel.best_travel.domain.repositories.mongo.AppUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class BestTravelApplication{


	public static void main(String[] args) {SpringApplication.run(BestTravelApplication.class, args);}
}
