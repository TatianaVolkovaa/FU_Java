package com.example.project.repository;

import com.example.project.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Интерфейс для класса {@link Country}
 */
public interface CountryRepository extends JpaRepository<Country, Integer> {
}
