package com.example.project.repository;

import com.example.project.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Интерфейс для класса {@link Client}
 */
public interface ClientRepository extends JpaRepository<Client, Integer> {
}
