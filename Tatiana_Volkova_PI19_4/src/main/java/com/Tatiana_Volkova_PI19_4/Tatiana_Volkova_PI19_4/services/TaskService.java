package com.Tatiana_Volkova_PI19_4.Tatiana_Volkova_PI19_4.services;

import com.Tatiana_Volkova_PI19_4.Tatiana_Volkova_PI19_4.entities.Task;
import com.Tatiana_Volkova_PI19_4.Tatiana_Volkova_PI19_4.repos.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository;

    public void create(Task task){
        taskRepository.save(task);
    }

    public List<Task> findAll(){
        return taskRepository.findAll();
    }

    public Optional<Task> findById(Long id){
        return taskRepository.findById(id);
    }

    public boolean update(Task task, Long id) {
        if (findById(id).isPresent()) {
            task.setId(id);
            taskRepository.save(task);
            return true;
        }
        return false;
    }

    public boolean delete(Long id) {
        if (findById(id).isPresent()) {
            taskRepository.deleteById(id);
        }
        return false;
    }
}
