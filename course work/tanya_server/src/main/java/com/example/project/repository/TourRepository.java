package com.example.project.repository;

import com.example.project.entity.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Интерфейс для класса {@link Tour}
 */
public interface TourRepository extends JpaRepository<Tour, Integer> {
    /**
     * Получение списка туров по id клиента
     * @param id идентификатор клиента
     * @return список туров по id клиента, отсортированный по дате
     */
    @Query(nativeQuery = true, value = "SELECT * FROM tours WHERE tour_id IN (SELECT tour_id FROM bookings WHERE client_id = :clientId) ORDER BY begin_date, end_date")
    List<Tour> findAllByClientId(@Param("clientId") int id);

}
