package com.example.project.controller;

import com.example.project.entity.City;
import com.example.project.service.CitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Контроллер для {@link City}
 */
@RestController
public class CitiesController {

    private CitiesService citiesService;

    @Autowired
    public CitiesController(CitiesService citiesService) {
        this.citiesService = citiesService;
    }

    /**
     * Получение списка городов по id страны
     * @param country_id идентификатор страны
     * @return OK и список городов, если список не пуст; NOT_FOUND если список пуст
     */
    @GetMapping("cities/{country_id}")
    public ResponseEntity<List<City>> getCitiesByCountry(@PathVariable(name = "country_id") Integer country_id) {
        List<City> cityList = citiesService.getCitiesByCountry(country_id);

        if (cityList == null || cityList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(cityList, HttpStatus.OK);
        }
    }

    /**
     * Получение списка всех городов
     * @return OK и список городов, если список не пуст; NOT_FOUND если список пуст
     */
    @GetMapping("cities")
    public ResponseEntity<List<City>> getCities() {
        List<City> cityList = citiesService.getAll();

        if (cityList == null || cityList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(cityList, HttpStatus.OK);
        }
    }

}
