package com.Tatiana_Volkova_PI19_4.Tatiana_Volkova_PI19_4.repos;

import com.Tatiana_Volkova_PI19_4.Tatiana_Volkova_PI19_4.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TaskRepository extends JpaRepository<Task, Long> {
}
