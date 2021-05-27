package com.example.project.controller;

import com.example.project.entity.Country;
import com.example.project.service.CountriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Контроллер для {@link Country}
 */
@RestController
public class CountriesController {

    private CountriesService countriesService;

    @Autowired
    public CountriesController(CountriesService countriesService) {
        this.countriesService = countriesService;
    }

    /**
     * Получение списка всех стран
     * @return OK и список стран, если список не пуст; NOT_FOUND если список пуст
     */
    @GetMapping("countries")
    public ResponseEntity<List<Country>> getAllCountries() {
        List<Country> countries = countriesService.findAllCountries();

        if (countries != null && !countries.isEmpty()) {
            return new ResponseEntity<>(countries, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
