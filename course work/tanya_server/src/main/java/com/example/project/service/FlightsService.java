package com.example.project.service;

import com.example.project.entity.Flight;
import com.example.project.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Сервис для {@link Flight}
 */
@Service
public class FlightsService {

    private FlightRepository flightRepository;

    @Autowired
    public FlightsService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    /**
     * Получение всех записей из авиаперелётов
     *
     * @return список всех авиаперелётов
     */
    public List<Flight> findAllFlights() {
        return flightRepository.findAll(Sort.by(Sort.Direction.DESC, "departureTime"));
    }

    /**
     * Получение авиаперелётов по id
     *
     * @param flight_id
     * @return найденный по id авиаперелёт
     */
    public Optional<Flight> findFlight(int flight_id) {
        return flightRepository.findById(flight_id);
    }

    /**
     * Создание авиаперелёта
     *
     * @param flight авиаперелёт
     * @return созданный авиаперелёт
     */
    public Flight createNewFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    /**
     * Изменение авиаперелёта
     *
     * @param flight авиаперелёт
     */
    public boolean updateFlight(Flight flight) {
        flightRepository.save(flight);
        return true
                ;
    }

    /**
     * Удаление авиаперелёта по id
     *
     * @param flight_id идентификатор авиаперелёта
     */
    public boolean deleteFlight(Integer flight_id) {
        flightRepository.deleteById(flight_id);
        return true;
    }
}
