package com.example.project.service;

import com.example.project.entity.Hotel;
import com.example.project.repository.HotelRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Optional;

@SpringBootTest
class HotelsServiceTest {

    @Autowired
    private HotelsService service;

    @MockBean
    private HotelRepository repository;

    @Test
    void findAllHotels() {
        ArrayList<Hotel> hotels = new ArrayList<>();
        Mockito.when(repository.findAll()).thenReturn(hotels);
        Assertions.assertEquals(hotels, service.findAllHotels());
    }

    @Test
    void findHotel() {
        Hotel hotel = new Hotel();
        hotel.setHotelId(10);
        Mockito.when(repository.findById(10)).thenReturn(Optional.of(hotel));
        Assertions.assertTrue(service.findHotel(10).isPresent());
        Assertions.assertEquals(hotel, service.findHotel(10).get());
    }

    @Test
    void createNewHotel() {
        Hotel weCreating = new Hotel();
        Mockito.when(repository.save(weCreating)).thenReturn(weCreating);
        Assertions.assertEquals(service.createNewHotel(weCreating), weCreating);
    }

    @Test
    void updateHotel() {
        Hotel weCreating = new Hotel();
        weCreating.setHotelId(1);
        Mockito.when(repository.findById(1)).thenReturn(Optional.of(new Hotel()));
        Mockito.when(repository.save(weCreating)).thenReturn(weCreating);
        Assertions.assertTrue(service.updateHotel(weCreating));
    }

    @Test
    void deleteHotel() {
        Mockito.when(repository.findById(1)).thenReturn(Optional.of(new Hotel()));
        Assertions.assertTrue(service.deleteHotel(1));
    }
}