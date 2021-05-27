package com.example.project.controller;

import com.example.project.entity.Booking;
import com.example.project.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Контроллер для {@link Booking}
 */
@RestController
public class BookingController {

    private BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    /**
     * Получение списка всех заказов
     * @return OK и список заказов, если список не пуст; NOT_FOUND, если список пуст
     */
    @GetMapping("bookings")
    public ResponseEntity<List<Booking>> getBookings() {
        List<Booking> bookings = bookingService.findAllBookings();

        if (bookings == null || bookings.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(bookings, HttpStatus.OK);
        }
    }


    /**
     * Получение списка заказов по id сотрудника
     * @param employee_id идентификатор сотрудника
     * @return OK и список заказов, если список не пуст; NOT_FOUND, если список пуст
     */
    @GetMapping("bookings/by_employee/{id}")
    public ResponseEntity<List<Booking>> getBookings(@PathVariable(name = "id") Integer employee_id) {
        List<Booking> bookings = bookingService.findByEmployeeId(employee_id);

        if (bookings == null || bookings.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(bookings, HttpStatus.OK);
        }
    }

    /**
     * Получение заказа по id
     * @param booking_id идентификатор заказа
     * @return OK и заказ, если заказ существует; NOT_FOUND если заказ не найден
     */
    @GetMapping("bookings/{id}")
    public ResponseEntity<?> getBooking(@PathVariable(name = "id") Integer booking_id) {
        Optional<Booking> booking = bookingService.findBooking(booking_id);

        if (booking.isPresent()) {
            return new ResponseEntity<>(booking, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Добавление заказа в список
     * @param booking заказ
     * @return добавленный заказ
     */
    @PostMapping("bookings")
    public ResponseEntity<?> postBooking(@RequestBody Booking booking) {
        Booking body = bookingService.createNewBooking(booking);
        return new ResponseEntity<>(body, HttpStatus.CREATED);
    }


    /**
     * Изменение существующего заказа
     * @param booking_id идентификатор заказа
     * @param booking новый (новая информация) заказ
     * @return OK, если заказ существует; NOT_FOUND, если заказ не найден
     */
    @PutMapping("bookings/{id}")
    public ResponseEntity<?> updateBooking(@PathVariable(name = "id") Integer booking_id, @RequestBody Booking booking) {
        Booking newBooking = new Booking();
        newBooking.setId(booking_id);
        newBooking.setClient(booking.getClient());
        newBooking.setDate(booking.getDate());
        newBooking.setDiscount(booking.getDiscount());
        newBooking.setEmployee(booking.getEmployee());
        newBooking.setNumberOfAdults(booking.getNumberOfAdults());
        newBooking.setNumberOfChildren(booking.getNumberOfChildren());
        newBooking.setStatus(booking.getStatus());
        newBooking.setTour(booking.getTour());

        if (bookingService.findBooking(booking_id).isPresent()) {
            bookingService.updateBooking(booking);
            return new ResponseEntity<>(HttpStatus.OK);

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Удаление заказа по id
     * @param booking_id идентификатор заказа
     * @return OK, если заказ существует; NOT_FOUND, если заказ не найден
     */
    @DeleteMapping("bookings/{id}")
    public ResponseEntity<?> deleteBooking(@PathVariable(name = "id") Integer booking_id) {
        if (bookingService.findBooking(booking_id).isPresent()) {
            bookingService.deleteBooking(booking_id);
            return new ResponseEntity<>(HttpStatus.OK);

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Удаление всех заказов
     * @return OK
     */
    @DeleteMapping("bookings")
    public ResponseEntity<?> deleteAllBookings() {
        bookingService.deleteAllBookings();
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
