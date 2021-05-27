package com.example.project.controller;

import com.example.project.entity.TourType;
import com.example.project.service.TourTypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Контроллер для {@link TourType}
 */
@RestController
public class TourTypesController {

    private TourTypesService tourTypesService;

    @Autowired
    public TourTypesController(TourTypesService tourTypesService) {
        this.tourTypesService = tourTypesService;
    }

    /**
     * Получение списка всех типов туров
     * @return OK и список типов туров, если список не пуст; NOT_FOUND, если список пуст
     */
    @GetMapping("tours/types")
    public ResponseEntity<List<TourType>> getAllTourTypes() {
        List<TourType> tourTypes = tourTypesService.findAllTourTypes();

        if (tourTypes != null && !tourTypes.isEmpty()) {
            return new ResponseEntity<>(tourTypes, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
