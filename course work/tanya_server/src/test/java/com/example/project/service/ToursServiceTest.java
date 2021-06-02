package com.example.project.service;

import com.example.project.entity.Hotel;
import com.example.project.entity.Tour;
import com.example.project.repository.TourRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Optional;

@SpringBootTest
class ToursServiceTest {

    @Autowired
    private ToursService service;

    @MockBean
    private TourRepository repository;

    @Test
    void findAlTours() {
        ArrayList<Tour> hotels = new ArrayList<>();
        Mockito.when(repository.findAll()).thenReturn(hotels);
        Assertions.assertEquals(hotels, service.findAlTours());
    }

    @Test
    void findByClientId() {
        ArrayList<Tour> hotels = new ArrayList<>();
        Mockito.when(repository.findAll()).thenReturn(hotels);
        Assertions.assertEquals(hotels, service.findAlTours());
    }

    @Test
    void findTour() {
        Tour tour = new Tour();
        tour.setTourId(10);
        Mockito.when(repository.findById(10)).thenReturn(Optional.of(tour));
        Assertions.assertTrue(service.findTour(10).isPresent());
        Assertions.assertEquals(tour, service.findTour(10).get());
    }

    @Test
    void createNewTour() {
        Tour weCreating = new Tour();
        Mockito.when(repository.save(weCreating)).thenReturn(weCreating);
        Assertions.assertEquals(service.createNewTour(weCreating), weCreating);
    }

    @Test
    void updateTour() {
        Tour weCreating = new Tour();
        weCreating.setTourId(1);
        Mockito.when(repository.findById(1)).thenReturn(Optional.of(new Tour()));
        Mockito.when(repository.save(weCreating)).thenReturn(weCreating);
        Assertions.assertTrue(service.updateTour(weCreating));
    }

    @Test
    void deleteTour() {
        Mockito.when(repository.findById(1)).thenReturn(Optional.of(new Tour()));
        Assertions.assertTrue(service.deleteTour(1));
    }
}