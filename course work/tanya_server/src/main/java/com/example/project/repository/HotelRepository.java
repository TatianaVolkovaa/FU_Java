package com.example.project.repository;

import com.example.project.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Интерфейс для класса {@link Hotel}
 */
public interface HotelRepository extends JpaRepository<Hotel, Integer> {
}
