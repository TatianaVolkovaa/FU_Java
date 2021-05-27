package com.example.project.service;

import com.example.project.entity.City;
import com.example.project.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Сервис для {@link City}
 */
@Service
public class CitiesService {

    private CityRepository cityRepository;

    @Autowired
    public CitiesService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    /**
     * Получение городов по id страны, в которой он находится
     * @param country_id идентификатор страны
     * @return списог городов в определенной стране
     */
    public List<City> getCitiesByCountry(int country_id) {
        return cityRepository.getCitiesByCountry(country_id);
    }

    /**
     * Получение всех записей из городов
     * @return список всех городов
     */
    public List<City> getAll() {
        return cityRepository.findAll();
    }

    /**
     * Создание города
     * @param city название города
     * @return созданный город
     */
    public City createNewCity(City city) {
        return cityRepository.save(city);
    }

    /**
     * Удаление города
     * @param city_id идентификатор города
     */
    public void deleteClient(Integer city_id) {
        cityRepository.deleteById(city_id);
    }
}
