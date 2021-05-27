package com.example.project.repository;

import com.example.project.entity.Feeding;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Интерфейс для класса {@link Feeding}
 */
public interface FeedingRepository extends JpaRepository<Feeding, Integer> {
}
