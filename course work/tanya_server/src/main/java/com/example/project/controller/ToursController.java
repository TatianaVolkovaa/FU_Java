package com.example.project.controller;

import com.example.project.entity.Tour;
import com.example.project.service.ToursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Контроллер для {@link Tour}
 */
@RestController
public class ToursController {

    private ToursService toursService;

    @Autowired
    public ToursController(ToursService toursService) {
        this.toursService = toursService;
    }

    /**
     * Получение списка всех туров
     * @return OK и список туров, если список не пуст; NOT_FOUND, если список пуст
     */
    @GetMapping("tours")
    public ResponseEntity<List<Tour>> getTours() {
        List<Tour> tours = toursService.findAlTours();

        if (tours == null || tours.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(tours, HttpStatus.OK);
        }
    }

    /**
     * Получение списка туров по id клиента
     * @param clientId идентификатор клиента
     * @return OK и список туров, если список не пуст; NOT_FOUND, если список пуст
     */
    @GetMapping("tours/by_client/{id}")
    public ResponseEntity<?> getClientTours(@PathVariable(name = "id") Integer clientId) {
        List<Tour> tours = toursService.findByClientId(clientId);

        if (tours == null || tours.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(tours, HttpStatus.OK);
        }
    }

    /**
     * Добавление тура в список
     * @param tour тур
     * @return добавленный тур
     */
    @PostMapping("tours")
    public ResponseEntity<?> postTour(@RequestBody Tour tour) {
        Tour body = toursService.createNewTour(tour);
        return new ResponseEntity<>(body, HttpStatus.CREATED);
    }

    /**
     * Изменение существующего тура
     * @param tourId идентификатор тура
     * @param tour новый (новая информация) тур
     * @return OK, если тур существует; NOT_FOUND, если тур не найден
     */
    @PutMapping("tours/{id}")
    public ResponseEntity<?> updateTour(@PathVariable(name = "id") Integer tourId, @RequestBody Tour tour) {
        Tour newTour = new Tour();
        newTour.setBeginDate(tour.getBeginDate());
        newTour.setCities(tour.getCities());
        newTour.setDescription(tour.getDescription());
        newTour.setEndDate(tour.getEndDate());
        newTour.setFlights(tour.getFlights());
        newTour.setHotels(tour.getHotels());
        newTour.setName(tour.getName());
        newTour.setPriceForOnePerson(tour.getPriceForOnePerson());
        newTour.setTourId(tourId);
        newTour.setTourType(tour.getTourType());

        if (toursService.findTour(tourId).isPresent()) {
            toursService.updateTour(tour);
            return new ResponseEntity<>(HttpStatus.OK);

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Удаление тура по id
     * @param tourId идентификатор тура
     * @return OK, если тур существует; NOT_FOUND, если тур не найден
     */
    @DeleteMapping("tours/{id}")
    public ResponseEntity<?> deleteTour(@PathVariable(name = "id") Integer tourId) {
        if (toursService.findTour(tourId).isPresent()) {
            toursService.deleteTour(tourId);
            return new ResponseEntity<>(HttpStatus.OK);

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
