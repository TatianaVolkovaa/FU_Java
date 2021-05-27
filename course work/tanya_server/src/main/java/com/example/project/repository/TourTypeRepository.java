package com.example.project.repository;

import com.example.project.entity.TourType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Интерфейс для класса {@link TourType}
 */
public interface TourTypeRepository extends JpaRepository<TourType, Integer> {
}
