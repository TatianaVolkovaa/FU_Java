package com.example.project.service;

import com.example.project.entity.TourType;
import com.example.project.repository.TourTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Сервис для {@link TourType}
 */
@Service
public class TourTypesService {

    private TourTypeRepository tourTypeRepository;

    @Autowired
    public TourTypesService(TourTypeRepository tourTypeRepository) {
        this.tourTypeRepository = tourTypeRepository;
    }

    /**
     * Получение всех записей типов туров
     * @return список всех типов туров
     */
    public List<TourType> findAllTourTypes() {
        return tourTypeRepository.findAll();
    }
}
