package com.example.project.repository;

import com.example.project.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Интерфейс для класса {@link Booking}
 */
public interface BookingRepository extends JpaRepository<Booking, Integer> {

    /**
     * Получение списка заказов по id сотрудника, который их оформил
     * @param id идентификатор сотрудника
     * @return список заказов по id сотрудника, который их оформил, отсортированный по дате
     */
    @Query(nativeQuery = true, value = "SELECT * FROM bookings WHERE employee_id = :id ORDER BY date DESC")
    List<Booking> findByEmployeeId(@Param("id") int id);

}
