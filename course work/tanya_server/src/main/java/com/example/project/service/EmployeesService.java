package com.example.project.service;

import com.example.project.entity.Employee;
import com.example.project.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Сервис для {@link Employee}
 */
@Service
public class EmployeesService {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeesService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    /**
     * Получение всех записей из сотрудников
     * @return список всех сотрудников
     */
    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll(Sort.by(Sort.Direction.ASC, "pos", "name", "surname", "patronymic"));
    }

    /**
     * Создание сотрудника
     * @param employee сотрудник
     * @return созданный сотрудник
     */
    public Employee createNewEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    /**
     * Изменение сотрудника
     * @param employee сотрудник
     */
    public boolean updateClient(Employee employee) {
        employeeRepository.save(employee);
        return true;
    }

    /**
     * Получение сотрудника по id
     * @param emp_id идентификатор сотрудника
     * @return найденный по id сотрудник
     */
    public Optional<Employee> findEmployee(int emp_id) {
        return employeeRepository.findById(emp_id);
    }

    /**
     * Проверка существования сотрудника по email, правильности логина и пароля
     * @param employee сотрудник
     * @return сотрудник, если он существует и пароль верен; ничего, если сотрудник не найден или пароль не аерен
     */
    public Employee loginEmployee(Employee employee) {
        Employee existing = employeeRepository.findByEmail(employee.getEmail());
        if (existing != null) {
            if (existing.getPass().equals(employee.getPass())) {
                return existing;
            } else return null;
        }

        return null;
    }
}
