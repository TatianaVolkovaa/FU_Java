package com.example.project.service;

import com.example.project.entity.Booking;
import com.example.project.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Сервис для {@link Booking}
 */
@Service
public class BookingService {

    private BookingRepository bookingRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    /**
     * Получение всех записей из заказов
     * @return список всех заказов
     */
    public List<Booking> findAllBookings() {
        return bookingRepository.findAll(Sort.by(Sort.Direction.DESC, "date"));
    }

    /**
     * Получение заказов по id сотрудника, который их оформлял
     * @param id идентификатор сотрудника
     * @return список заказов по id сотрудника, который их оформлял
     */
    public List<Booking> findByEmployeeId(int id) {
        return bookingRepository.findByEmployeeId(id);
    }

    /**
     * Получение заказа по его id
     * @param booking_id идентификатор сотрудника
     * @return найденный по id заказ
     */
    public Optional<Booking> findBooking(int booking_id) {
        return bookingRepository.findById(booking_id);
    }

    /**
     * Создание заказа
     * @param booking заказ
     * @return созданный заказ
     */
    public Booking createNewBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    /**
     * Изменение заказа
     * @param booking заказ
     */
    public void updateBooking(Booking booking) {
        bookingRepository.save(booking);
    }

    /**
     * Удаление заказа по id
     * @param booking_id идентификатор заказа
     */
    public void deleteBooking(Integer booking_id) {
        bookingRepository.deleteById(booking_id);
    }

    /**
     * Удаление всех заказов
     */
    public void deleteAllBookings() {
        bookingRepository.deleteAll();
    }

}
