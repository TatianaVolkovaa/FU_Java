package com.example.project.service;

import com.example.project.entity.Booking;
import com.example.project.entity.Employee;
import com.example.project.repository.BookingRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Optional;

@SpringBootTest
class BookingServiceTest {

    @Autowired
    private BookingService service;

    @MockBean
    private BookingRepository repository;

    @Test
    void findAllBookings() {
        ArrayList<Booking> bookings = new ArrayList<>();
        Mockito.when(repository.findAll()).thenReturn(bookings);
        Assertions.assertEquals(bookings, service.findAllBookings());
    }

    @Test
    void findByEmployeeId() {
        Employee employee = new Employee();
        employee.setName("Татьяна");
        employee.setEmployeeId(12);
        ArrayList<Booking> bookings = new ArrayList<>();
        Mockito.when(repository.findByEmployeeId(12)).thenReturn(bookings);
        Assertions.assertEquals(bookings, service.findByEmployeeId(12));
    }

    @Test
    void findBooking() {
        Booking booking = new Booking();
        booking.setId(10);
        Mockito.when(repository.findById(10)).thenReturn(Optional.of(booking));
        Assertions.assertTrue(service.findBooking(10).isPresent());
        Assertions.assertEquals(booking, service.findBooking(10).get());
    }

    @Test
    void createNewBooking() {
        Booking weCreating = new Booking();
        weCreating.setId(1); // ?
        Mockito.when(repository.save(weCreating)).thenReturn(weCreating);
        Assertions.assertEquals(service.createNewBooking(weCreating), weCreating);
    }

    @Test
    void updateBooking() {
        Booking weCreating = new Booking();
        weCreating.setId(1); // ?
        Mockito.when(repository.findById(1)).thenReturn(Optional.of(new Booking()));
        Mockito.when(repository.save(weCreating)).thenReturn(weCreating);
        Assertions.assertTrue(service.updateBooking(weCreating));
    }

    @Test
    void deleteBooking() {
        Mockito.when(repository.findById(1)).thenReturn(Optional.of(new Booking()));
        Assertions.assertTrue(service.deleteBooking(1));
    }
}