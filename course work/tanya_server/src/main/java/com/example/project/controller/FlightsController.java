package com.example.project.controller;

import com.example.project.entity.Flight;
import com.example.project.service.FlightsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Контроллер для {@link Flight}
 */
@RestController
public class FlightsController {

    private FlightsService flightsService;

    @Autowired
    public FlightsController(FlightsService flightsService) {
        this.flightsService = flightsService;
    }

    /**
     * Получение списка всех рейсов
     * @return OK и список рейсов, если список не пуст; NOT_FOUND, если список пуст
     */
    @GetMapping("flights")
    public ResponseEntity<List<Flight>> getFlights() {
        List<Flight> flights = flightsService.findAllFlights();

        if (flights == null || flights.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(flights, HttpStatus.OK);
        }
    }

    /**
     * Получение рейса по id
     * @param flightId идентификатор рейса
     * @return OK и рейс, если рейс существует; NOT_FOUND, если рейс не найден
     */
    @GetMapping("flights/{id}")
    public ResponseEntity<?> getFlight(@PathVariable(name = "id") Integer flightId) {
        Optional<Flight> flight = flightsService.findFlight(flightId);

        if (flight.isPresent()) {
            return new ResponseEntity<>(flight, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Добавление рейса в список
     * @param flight рейс
     * @return добавленный рейс
     */
    @PostMapping("flights")
    public ResponseEntity<?> postFlight(@RequestBody Flight flight) {
        Flight body = flightsService.createNewFlight(flight);
        return new ResponseEntity<>(body, HttpStatus.CREATED);
    }

    /**
     * Изменение существующего рейса
     * @param flightId идентификатор рейса
     * @param flight новый (новая информация) рейс
     * @return OK, если рейс существует; NOT_FOUND, если рейс не найден
     */
    @PutMapping("flights/{id}")
    public ResponseEntity<?> updateFlight(@PathVariable(name = "id") Integer flightId, @RequestBody Flight flight) {
        Flight newFlight = new Flight();
        newFlight.setFlightId(flightId);
        newFlight.setArrivalCity(flight.getArrivalCity());
        newFlight.setDepartureCity(flight.getDepartureCity());
        newFlight.setArrivalTime(flight.getArrivalTime());
        newFlight.setDepartureTime(flight.getDepartureTime());
        newFlight.setPrice(flight.getPrice());

        if (flightsService.findFlight(flightId).isPresent()) {
            flightsService.updateFlight(flight);
            return new ResponseEntity<>(HttpStatus.OK);

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Удаление рейса по id
     * @param flightId идентификатор рейса
     * @return OK, если рейс существует; NOT_FOUND, если рейс не найден
     */
    @DeleteMapping("flights/{id}")
    public ResponseEntity<?> deleteFlight(@PathVariable(name = "id") Integer flightId) {
        if (flightsService.findFlight(flightId).isPresent()) {
            flightsService.deleteFlight(flightId);
            return new ResponseEntity<>(HttpStatus.OK);

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
