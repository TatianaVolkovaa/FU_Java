package com.example.project.service;

import com.example.project.entity.Flight;
import com.example.project.repository.FlightRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Optional;

@SpringBootTest
class FlightsServiceTest {

    @Autowired
    private FlightsService service;

    @MockBean
    private FlightRepository repository;

    @Test
    void findAllFlights() {
        ArrayList<Flight> flights = new ArrayList<>();
        Mockito.when(repository.findAll()).thenReturn(flights);
        Assertions.assertEquals(flights, service.findAllFlights());
    }

    @Test
    void findFlight() {
        Flight flight = new Flight();
        flight.setFlightId(10);
        Mockito.when(repository.findById(10)).thenReturn(Optional.of(flight));
        Assertions.assertTrue(service.findFlight(10).isPresent());
        Assertions.assertEquals(flight, service.findFlight(10).get());
    }

    @Test
    void createNewFlight() {
        Flight weCreating = new Flight();
        weCreating.setFlightId(1); // ?
        Mockito.when(repository.save(weCreating)).thenReturn(weCreating);
        Assertions.assertEquals(service.createNewFlight(weCreating), weCreating);

    }

    @Test
    void updateFlight() {
        Flight weCreating = new Flight();
        weCreating.setFlightId(1); // ?
        Mockito.when(repository.findById(1)).thenReturn(Optional.of(new Flight()));
        Mockito.when(repository.save(weCreating)).thenReturn(weCreating);
        Assertions.assertTrue(service.updateFlight(weCreating));
    }

    @Test
    void deleteFlight() {
        Mockito.when(repository.findById(1)).thenReturn(Optional.of(new Flight()));
        Assertions.assertTrue(service.deleteFlight(1));
    }
}