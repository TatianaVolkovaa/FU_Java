package com.example.project.repository;

import com.example.project.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Интерфейс для класса {@link Flight}
 */
public interface FlightRepository extends JpaRepository<Flight, Integer> {
}
