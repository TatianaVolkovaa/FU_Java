package com.example.project.service;

import com.example.project.entity.Employee;
import com.example.project.repository.EmployeeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Optional;

@SpringBootTest
class EmployeesServiceTest {

    @Autowired
    private EmployeesService service;

    @MockBean
    private EmployeeRepository repository;

    @Test
    void findAllEmployees() {
        ArrayList<Employee> employees = new ArrayList<>();
        Mockito.when(repository.findAll()).thenReturn(employees);
        Assertions.assertEquals(employees, service.findAllEmployees());
    }

    @Test
    void createNewEmployee() {
        Employee weCreating = new Employee();
        weCreating.setEmployeeId(1);
        Mockito.when(repository.save(weCreating)).thenReturn(weCreating);
        Assertions.assertEquals(service.createNewEmployee(weCreating), weCreating);
    }

    @Test
    void updateClient() {
        Employee weCreating = new Employee();
        weCreating.setEmployeeId(1);
        Mockito.when(repository.findById(1)).thenReturn(Optional.of(new Employee()));
        Mockito.when(repository.save(weCreating)).thenReturn(weCreating);
        Assertions.assertTrue(service.updateClient(weCreating));
    }

    @Test
    void findEmployee() {
        Employee booking = new Employee();
        booking.setEmployeeId(10);
        Mockito.when(repository.findById(10)).thenReturn(Optional.of(booking));
        Assertions.assertTrue(service.findEmployee(10).isPresent());
        Assertions.assertEquals(booking, service.findEmployee(10).get());
    }

    @Test
    void loginEmployee() {
        Employee employee = new Employee();
        employee.setName("Татьяна");
        employee.setEmail("tanya@ya.ru");
        employee.setEmployeeId(1);
        employee.setPass("123312");

        Mockito.when(repository.findByEmail("tanya@ya.ru")).thenReturn(employee);
        Assertions.assertEquals(employee, service.loginEmployee(employee));
    }

    @Test
    void loginEmployee_NOT_FOUND() {
        Employee employee = new Employee();
        employee.setName("Татьяна");
        employee.setEmail("tanya@ya.ru");
        employee.setEmployeeId(1);

        Mockito.when(repository.findByEmail("tanya@ya.ru")).thenReturn(employee);
        Assertions.assertNull(service.loginEmployee(new Employee()));
    }

    @Test
    void loginEmployee_INCORRECT_PASS() {
        Employee employee = new Employee();
        employee.setName("Татьяна");
        employee.setEmail("tanya@ya.ru");
        employee.setEmployeeId(1);
        employee.setPass("123231");

        Employee employee1 = new Employee();
        employee1.setName("Татьяна");
        employee1.setEmail("tanya@ya.ru");
        employee1.setEmployeeId(1);
        employee1.setPass("1134");


        Mockito.when(repository.findByEmail("tanya@ya.ru")).thenReturn(employee);
        Assertions.assertNull(service.loginEmployee(employee1));
    }
}