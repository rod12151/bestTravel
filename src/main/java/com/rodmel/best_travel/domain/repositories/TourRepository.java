package com.rodmel.best_travel.domain.repositories;

import com.rodmel.best_travel.domain.entities.TourEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TourRepository extends CrudRepository<TourEntity,Long> {
}
