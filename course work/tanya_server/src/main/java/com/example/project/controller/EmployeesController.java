package com.example.project.controller;

import com.example.project.entity.Employee;
import com.example.project.service.EmployeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Контроллер для {@link Employee}
 */
@RestController
@RequestMapping(produces = "application/json;charset=UTF-8")
public class EmployeesController {

    private EmployeesService employeesService;

    @Autowired
    public EmployeesController(EmployeesService employeesService) {
        this.employeesService = employeesService;
    }

    /**
     * Получение списка всех сотрудников
     * @return OK и список заказов, если список не пуст; NOT_FOUND если список пуст
     */
    @GetMapping("employees")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeesService.findAllEmployees();

        if (employees != null && !employees.isEmpty()) {
            return new ResponseEntity<>(employees, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Добавление сотрудника в список
     * @param employee сотрудник
     * @return добавленного сотрудника
     */
    @PostMapping("employees")
    public ResponseEntity<?> postEmployee(@RequestBody Employee employee) {
        Employee body = employeesService.createNewEmployee(employee);
        return new ResponseEntity<>(body, HttpStatus.CREATED);
    }

    /**
     *
     * @param employee сотрудник
     * @return OK и зарегистрированный сотрудник, если сотрудник существует; NOT_FOUND, если сотрудник не найден
     */
    @PostMapping("employees/login")
    public ResponseEntity<Employee> loginEmployee(@RequestBody Employee employee) {
        Employee logined = employeesService.loginEmployee(employee);
        if (logined != null) {
            return new ResponseEntity<>(logined, HttpStatus.OK);

        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    /**
     * Изменение существующего сотрудника
     * @param emp_id идентификатор сотрудника
     * @param employee новый (новая информация) сотрудник
     * @return OK, если сотрудник существует; NOT_FOUND, если сотрудник не найден
     */
    @PutMapping("employees/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable(name = "id") Integer emp_id, @RequestBody Employee employee) {
        Employee newEmployee = new Employee();
        newEmployee.setEmployeeId(emp_id);
        newEmployee.setBirthdate(employee.getBirthdate());
        newEmployee.setEmail(employee.getEmail());
        newEmployee.setGender(employee.getGender());
        newEmployee.setName(employee.getName());
        newEmployee.setPatronymic(employee.getPatronymic());
        newEmployee.setSurname(employee.getSurname());
        newEmployee.setPhoneNumber(employee.getPhoneNumber());
        newEmployee.setPhoneNumber2(employee.getPhoneNumber2());

        if (employeesService.findEmployee(emp_id).isPresent()) {
            employeesService.updateClient(employee);
            return new ResponseEntity<>(HttpStatus.OK);

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
