package com.rodmel.best_travel.domain.repositories;

import com.rodmel.best_travel.domain.entities.HotelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<HotelEntity,Long> {
}
