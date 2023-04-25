package com.rodmel.best_travel.infraestructure.helpers;

import com.rodmel.best_travel.domain.repositories.CustomerRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Transactional
@Component
@AllArgsConstructor
public class CustomerHelper {
    private final CustomerRepository customerRepository;

    public void  incrase(String customerId, Class<?>type){
        var customerToUpdate = this.customerRepository.findById(customerId).orElseThrow();

        switch (type.getSimpleName()){
            case "TourService"->customerToUpdate.setTotalTours(customerToUpdate.getTotalTours() + 1);
            case "TicketService"->customerToUpdate.setTotalFlights(customerToUpdate.getTotalFlights() + 1);
            case "ReservationService"->customerToUpdate.setTotalLodgings(customerToUpdate.getTotalLodgings() + 1);
        }
        this.customerRepository.save(customerToUpdate);
    }
    /* public void desincrase(String customerId, Class<?>type){
        var customerToUpdate = this.customerRepository.findById(customerId).orElseThrow();

        switch (type.getSimpleName()){
            case "TourService"->customerToUpdate.setTotalTours(customerToUpdate.getTotalTours() - 1);
            case "TicketService"->customerToUpdate.setTotalFlights(customerToUpdate.getTotalFlights() - 1);
            case "ReservationService"->customerToUpdate.setTotalLodgings(customerToUpdate.getTotalLodgings() - 1);
        }
        this.customerRepository.save(customerToUpdate);

    }*/


}
