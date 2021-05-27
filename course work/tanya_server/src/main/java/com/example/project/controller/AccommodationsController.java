package com.example.project.controller;

import com.example.project.entity.AccommodationType;
import com.example.project.service.AccommodationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Контроллер для {@link AccommodationType}
 */

@RestController
public class AccommodationsController {

    private AccommodationsService accommodationsService;

    @Autowired
    public AccommodationsController(AccommodationsService accommodationsService) {
        this.accommodationsService = accommodationsService;
    }

    /**
     * Получение списка все типов проживания
     * @return OK и список категорий, если список не пусть; NOT_FOUND если список пуст
     */
    @GetMapping("accommodations")
    public ResponseEntity<List<AccommodationType>> getAccommodations() {
        List<AccommodationType> accommodations = accommodationsService.getAccommodationTypes();

        if (accommodations == null || accommodations.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(accommodations, HttpStatus.OK);
        }
    }

}
