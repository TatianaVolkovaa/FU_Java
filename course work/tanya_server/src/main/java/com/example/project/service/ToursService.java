package com.example.project.service;

import com.example.project.entity.Tour;
import com.example.project.repository.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Сервис для {@link Tour}
 */
@Service
public class ToursService {

    private TourRepository tourRepository;

    @Autowired
    public ToursService(TourRepository tourRepository) {
        this.tourRepository = tourRepository;
    }

    /**
     * Получение всех записей туров
     *
     * @return список всех туров
     */
    public List<Tour> findAlTours() {
        return tourRepository.findAll(Sort.by(Sort.Direction.DESC, "beginDate", "endDate"));
    }

    /**
     * Получение списка туров по id клиента
     *
     * @param id идентификатор клиента
     * @return список туров по id клиента
     */
    public List<Tour> findByClientId(int id) {
        return tourRepository.findAllByClientId(id);
    }

    /**
     * Получение тура по его id
     *
     * @param tourId идентификатор тура
     * @return найденный по id тур
     */
    public Optional<Tour> findTour(int tourId) {
        return tourRepository.findById(tourId);
    }

    /**
     * Создание нового тура
     *
     * @param tour тур
     * @return созданный тур
     */
    public Tour createNewTour(Tour tour) {
        return tourRepository.save(tour);
    }

    /**
     * Изменение тура
     *
     * @param tour тур
     */
    public boolean updateTour(Tour tour) {
        tourRepository.save(tour);
        return true;
    }

    /**
     * Удаление тура по id
     *
     * @param tourId идентификатор тура
     */
    public boolean deleteTour(Integer tourId) {
        tourRepository.deleteById(tourId);
        return true;
    }

}
