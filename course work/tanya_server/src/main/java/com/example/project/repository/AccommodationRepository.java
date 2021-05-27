package com.example.project.repository;

import com.example.project.entity.AccommodationType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Интерфейс для класса {@link AccommodationType}
 */
public interface AccommodationRepository extends JpaRepository<AccommodationType, Integer> {
}
