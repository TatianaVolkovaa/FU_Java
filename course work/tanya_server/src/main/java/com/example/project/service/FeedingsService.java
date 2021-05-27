package com.example.project.service;

import com.example.project.entity.Feeding;
import com.example.project.repository.FeedingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Сервис для {@link Feeding}
 */
@Service
public class FeedingsService {

    private FeedingRepository feedingRepository;

    @Autowired
    public FeedingsService(FeedingRepository feedingRepository) {
        this.feedingRepository = feedingRepository;
    }

    /**
     * Получение всех записей из типов питания
     * @return список всех типов питания
     */
    public List<Feeding> findAllFeedings() {
        return feedingRepository.findAll();
    }
}
