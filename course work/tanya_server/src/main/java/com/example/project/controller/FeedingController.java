package com.example.project.controller;

import com.example.project.entity.Feeding;
import com.example.project.service.FeedingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Контроллер для {@link Feeding}
 */
@RestController
public class FeedingController {

    private FeedingsService feedingsService;

    @Autowired
    public FeedingController(FeedingsService feedingsService) {
        this.feedingsService = feedingsService;
    }

    /**
     * Получение списка всех типов питания
     * @return OK и список типов питания, если список не пуст; NOT_FOUND, если список пуст
     */
    @GetMapping("feedings")
    public ResponseEntity<List<Feeding>> getAllFeedings() {
        List<Feeding> feedings = feedingsService.findAllFeedings();

        if (feedings != null && !feedings.isEmpty()) {
            return new ResponseEntity<>(feedings, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
