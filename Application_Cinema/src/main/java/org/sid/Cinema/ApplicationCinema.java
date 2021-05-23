package org.sid.Cinema; 

import org.sid.Cinema.service.ICinemaInitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
 
@SpringBootApplication
public class ApplicationCinema implements CommandLineRunner{
 
    @Autowired
    private ICinemaInitService cinemaInitService;

    public static void main(String[] args) {
        SpringApplication.run(ApplicationCinema.class, args);
    }
 
    @Override
    public void run(String... args) throws Exception {
        cinemaInitService.initVillees();
        cinemaInitService.initCinemas();
        cinemaInitService.initSalles();
        cinemaInitService.initPlaces();
        cinemaInitService.initSeances();
        cinemaInitService.initCategories();
        cinemaInitService.initfilms();
        cinemaInitService.initProjections();
        cinemaInitService.initTickets();


    }
 
}