package com.example.project.service;

import com.example.project.entity.Country;
import com.example.project.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Сервис для {@link Country}
 */
@Service
public class CountriesService {

    private CountryRepository countryRepository;

    @Autowired
    public CountriesService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    /**
     * Получение всех записей из стран
     * @return список всех стран
     */
    public List<Country> findAllCountries() {
        return countryRepository.findAll();
    }
}
