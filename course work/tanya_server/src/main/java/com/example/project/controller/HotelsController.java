package com.example.project.controller;

import com.example.project.entity.Hotel;
import com.example.project.service.HotelsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Контроллер для {@link Hotel}
 */
@RestController
public class HotelsController {

    private HotelsService hotelsService;

    @Autowired
    public HotelsController(HotelsService hotelsService) {
        this.hotelsService = hotelsService;
    }

    /**
     * Получение списка всех отелей
     * @return OK и список отелей, если список не пуст; NOT_FOUND, если список пуст
     */
    @GetMapping("hotels")
    public ResponseEntity<List<Hotel>> getHotels() {
        List<Hotel> hotels = hotelsService.findAllHotels();

        if (hotels == null || hotels.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(hotels, HttpStatus.OK);
        }
    }

    /**
     * Получение отеля по id
     * @param hotelId идентификатор заказа
     * @return OK и отель, если отель существует; NOT_FOUND, если отель не найден
     */
    @GetMapping("hotels/{id}")
    public ResponseEntity<?> getHotel(@PathVariable(name = "id") Integer hotelId) {
        Optional<Hotel> hotel = hotelsService.findHotel(hotelId);

        if (hotel.isPresent()) {
            return new ResponseEntity<>(hotel, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Добавление отеля в список
     * @param hotel отель
     * @return добавленный отель
     */
    @PostMapping("hotels")
    public ResponseEntity<?> postHotel(@RequestBody Hotel hotel) {
        Hotel body = hotelsService.createNewHotel(hotel);
        return new ResponseEntity<>(body, HttpStatus.CREATED);
    }

    /**
     * Изменение существующего отеля
     * @param hotelId идентификатор отеля
     * @param hotel новый (новая информация) отель
     * @return OK, если отель существует; NOT_FOUND, если отель не найден
     */
    @PutMapping("hotels/{id}")
    public ResponseEntity<?> updateHotel(@PathVariable(name = "id") Integer hotelId, @RequestBody Hotel hotel) {
        Hotel newHotel = new Hotel();
        newHotel.setAccommodationType(hotel.getAccommodationType());
        newHotel.setCity(hotel.getCity());
        newHotel.setFeeding(hotel.getFeeding());
        newHotel.setHotelId(hotelId);
        newHotel.setName(hotel.getName());
        newHotel.setNumberOfStars(hotel.getNumberOfStars());
        newHotel.setPriceForOneNight(hotel.getPriceForOneNight());

        if (hotelsService.findHotel(hotelId).isPresent()) {
            hotelsService.updateHotel(hotel);
            return new ResponseEntity<>(HttpStatus.OK);

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Удаление отеля по id
     * @param hotelId идентификатор отеля
     * @return OK, если отель существует; NOT_FOUND, если отель не найден
     */
    @DeleteMapping("hotels/{id}")
    public ResponseEntity<?> deleteHotel(@PathVariable(name = "id") Integer hotelId) {
        if (hotelsService.findHotel(hotelId).isPresent()) {
            hotelsService.deleteHotel(hotelId);
            return new ResponseEntity<>(HttpStatus.OK);

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
