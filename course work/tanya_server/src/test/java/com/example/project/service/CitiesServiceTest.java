package com.example.project.service;

import com.example.project.entity.City;
import com.example.project.repository.CityRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;

@SpringBootTest
class CitiesServiceTest {

    @Autowired
    private CitiesService service;

    @MockBean
    private CityRepository repository;

    @Test
    void getCitiesByCountry() {
        ArrayList<City> cities = new ArrayList<>();
        Mockito.when(repository.getCitiesByCountry(10)).thenReturn(cities);
        Assertions.assertEquals(cities, service.getCitiesByCountry(10));
    }

    @Test
    void getAll() {
        ArrayList<City> cities = new ArrayList<>();
        Mockito.when(repository.findAll()).thenReturn(cities);
        Assertions.assertEquals(cities, service.getAll());
    }
}