package com.Tatiana_Volkova_PI19_4.Tatiana_Volkova_PI19_4.controllers;

import com.Tatiana_Volkova_PI19_4.Tatiana_Volkova_PI19_4.entities.Category;
import com.Tatiana_Volkova_PI19_4.Tatiana_Volkova_PI19_4.services.CategoryService;
import com.Tatiana_Volkova_PI19_4.Tatiana_Volkova_PI19_4.entities.Client;
import com.Tatiana_Volkova_PI19_4.Tatiana_Volkova_PI19_4.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping(value = "/categories")
    public ResponseEntity<List<Category>> readCategory(){
        final List<Category> categoryList = categoryService.findAll();

        if (categoryList != null) {
            return new ResponseEntity<>(categoryList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/categories/{id}")
    public ResponseEntity<Optional<Category>> readCategory(@PathVariable(name = "id" ) Long id){
        final Optional<Category> category = categoryService.findById(id);

        if (category.isPresent()) {
            return new ResponseEntity<>(category , HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/categories")
    public void createCategory(@RequestBody Category category){
        categoryService.create(category);
    }

    @PutMapping(value = "/categories/{id}")
    public ResponseEntity<Object> updateCategory(@RequestBody Category category, @PathVariable(name = "id") Long id) {
        if (categoryService.update(category, id)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/categories/{id}")
    public ResponseEntity<Object> deleteCategory(@PathVariable(name = "id") Long id){
        if (categoryService.delete(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}
