package com.rodmel.best_travel.domain.repositories.jpa;

import com.rodmel.best_travel.domain.entities.jpa.TourEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TourRepository extends CrudRepository<TourEntity,Long> {
}
