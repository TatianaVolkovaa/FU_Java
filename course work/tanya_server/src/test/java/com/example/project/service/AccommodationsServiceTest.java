package com.example.project.service;

import com.example.project.entity.AccommodationType;
import com.example.project.repository.AccommodationRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;

@SpringBootTest
class AccommodationsServiceTest {

    @Autowired
    private AccommodationsService service;

    @MockBean
    private AccommodationRepository repository;

    @Test
    void getAccommodationTypes() {
        ArrayList<AccommodationType> accommodationTypes = new ArrayList<>();
        Mockito.when(repository.findAll()).thenReturn(accommodationTypes);
        Assertions.assertEquals(accommodationTypes, service.getAccommodationTypes());
    }
}