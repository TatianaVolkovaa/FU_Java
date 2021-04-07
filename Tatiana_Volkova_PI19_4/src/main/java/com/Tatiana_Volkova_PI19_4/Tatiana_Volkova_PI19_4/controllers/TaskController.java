package com.Tatiana_Volkova_PI19_4.Tatiana_Volkova_PI19_4.controllers;

import com.Tatiana_Volkova_PI19_4.Tatiana_Volkova_PI19_4.entities.Task;
import com.Tatiana_Volkova_PI19_4.Tatiana_Volkova_PI19_4.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping(value = "/tasks")
    public ResponseEntity<List<Task>> readTask(){
        final List<Task> taskList = taskService.findAll();

        if (taskList != null) {
            return new ResponseEntity<>(taskList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/tasks/{id}")
    public ResponseEntity<Optional<Task>> readTask(@PathVariable(name = "id" ) Long id){
        final Optional<Task> task = taskService.findById(id);

        if (task.isPresent()) {
            return new ResponseEntity<>(task , HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/tasks")
    public void createTask(@RequestBody Task task){
        taskService.create(task);
    }

    @PutMapping(value = "/tasks/{id}")
    public ResponseEntity<Object> updateTask(@RequestBody Task task, @PathVariable(name = "id") Long id) {
        if (taskService.update(task, id)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/tasks/{id}")
    public ResponseEntity<Object> deleteTask(@PathVariable(name = "id") Long id){
        if (taskService.delete(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}
