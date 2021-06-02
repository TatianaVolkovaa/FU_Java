package com.example.project.service;

import com.example.project.entity.TourType;
import com.example.project.repository.TourTypeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;

@SpringBootTest
class TourTypesServiceTest {

    @Autowired
    private TourTypesService service;

    @MockBean
    private TourTypeRepository repository;

    @Test
    void findAllTourTypes() {
        ArrayList<TourType> tourTypes = new ArrayList<>();
        Mockito.when(repository.findAll()).thenReturn(tourTypes);
        Assertions.assertEquals(tourTypes, service.findAllTourTypes());
    }
}