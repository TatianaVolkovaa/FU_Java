package com.example.project.service;

import com.example.project.entity.Country;
import com.example.project.repository.CountryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;

@SpringBootTest
class CountriesServiceTest {

    @Autowired
    private CountriesService service;

    @MockBean
    private CountryRepository repository;

    @Test
    void findAllCountries() {
        ArrayList<Country> countries = new ArrayList<>();
        Mockito.when(repository.findAll()).thenReturn(countries);
        Assertions.assertEquals(countries, service.findAllCountries());
    }

    @Test
    void findAllCountries_NOT_FOUND() {
        ArrayList<Country> countries = new ArrayList<>();
        Mockito.when(repository.findAll()).thenReturn(null);
        Assertions.assertEquals(countries, service.findAllCountries());
    }
}