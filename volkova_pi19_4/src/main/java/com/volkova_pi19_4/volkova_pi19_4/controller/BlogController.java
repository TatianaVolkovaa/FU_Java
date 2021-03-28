package com.volkova_pi19_4.volkova_pi19_4.controller;

import com.volkova_pi19_4.volkova_pi19_4.entity.Blog;
import com.volkova_pi19_4.volkova_pi19_4.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BlogController {
    @Autowired
    private BlogService blogService;

    @PostMapping(value = "/blogs/")
    public void createBlog(@RequestBody Blog blog) {blogService.create(blog);}

    @GetMapping(value = "/blogs/{id}")
    public ResponseEntity<Optional<Blog>> readBlog(@PathVariable(name="id") Long id){
        final Optional<Blog> blog = blogService.findById(id);
        if (blog.isPresent()){
            return new ResponseEntity<>(blog, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/blogs/")
    public ResponseEntity<List<Blog>> readPerson(){
        final List<Blog> personList = blogService.findAll();

        if (personList != null) {
            return new ResponseEntity<>(personList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
