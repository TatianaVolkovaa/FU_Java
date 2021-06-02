package com.example.project.service;

import com.example.project.entity.Feeding;
import com.example.project.repository.FeedingRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;

@SpringBootTest
class FeedingsServiceTest {

    @Autowired
    private FeedingsService service;

    @MockBean
    private FeedingRepository repository;

    @Test
    void findAllFeedings() {
        ArrayList<Feeding> feedings = new ArrayList<>();
        Mockito.when(repository.findAll()).thenReturn(feedings);
        Assertions.assertEquals(feedings, service.findAllFeedings());
    }
}