package com.example.project.service;

import com.example.project.entity.Hotel;
import com.example.project.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Сервис для {@link Hotel}
 */
@Service
public class HotelsService {

    private HotelRepository hotelRepository;

    @Autowired
    public HotelsService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    /**
     * Получение всех записей из отелей
     *
     * @return список всех отелей
     */
    public List<Hotel> findAllHotels() {
        return hotelRepository.findAll(Sort.by(Sort.Direction.DESC, "numberOfStars"));
    }

    /**
     * Получение отелей по id
     *
     * @param hotel_id идентификатор отеля
     * @return найденный по id отель
     */
    public Optional<Hotel> findHotel(int hotel_id) {
        return hotelRepository.findById(hotel_id);
    }

    /**
     * Создание отеля
     *
     * @param hotel отель
     * @return созданный отель
     */
    public Hotel createNewHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    /**
     * Изменение отеля
     *
     * @param hotel отель
     */
    public boolean updateHotel(Hotel hotel) {
        hotelRepository.save(hotel);
        return true;
    }

    /**
     * Удаление отеля по id
     *
     * @param hotel_id идентификатор отеля
     */
    public boolean deleteHotel(Integer hotel_id) {
        hotelRepository.deleteById(hotel_id);
        return true;
    }
}
