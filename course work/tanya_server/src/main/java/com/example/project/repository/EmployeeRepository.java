package com.example.project.repository;

import com.example.project.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Интерфейс для класса {@link Employee}
 */
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    /**
     * /**
     * Получение сотрудника по email
     * @param email почта сотрудника
     * @return сотрудник по email
     */
    @Query(nativeQuery = true, value = "SELECT * FROM employees WHERE email = :email")
    Employee findByEmail(@Param("email") String email);

}
