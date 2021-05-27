package com.example.project.repository;

import com.example.project.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Интерфейс для класса {@link City}
 */
public interface CityRepository extends JpaRepository<City, Integer> {
    /**
     * Получение списка городов по id страны, в которой они находятся
     * @param country_id идентификатор страны
     * @return список городов по id страны, в которой они находятся
     */
    @Query(nativeQuery = true, value = "SELECT * FROM CITY WHERE COUNTRY_ID = :country_id")
    public List<City> getCitiesByCountry(@Param("country_id") int country_id);

}
