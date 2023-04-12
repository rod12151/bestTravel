package com.rodmel.best_travel.domain.repositories;

import com.rodmel.best_travel.domain.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<CustomerEntity,String> {

    Optional<CustomerEntity> findByDni(String s);
}
