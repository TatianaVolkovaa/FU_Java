package com.example.project.service;

import com.example.project.entity.AccommodationType;
import com.example.project.repository.AccommodationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Сервис для {@link AccommodationType}
 */
@Service
public class AccommodationsService {

    private AccommodationRepository accommodationRepository;

    @Autowired
    public AccommodationsService(AccommodationRepository accommodationRepository) {
        this.accommodationRepository = accommodationRepository;
    }

    /**
     * Получение всех записей из типов проживания
     * @return список всех типов проживания
     */
    public List<AccommodationType> getAccommodationTypes() {
        return accommodationRepository.findAll();
    }
}
