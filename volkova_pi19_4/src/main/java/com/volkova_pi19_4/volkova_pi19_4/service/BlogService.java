package com.volkova_pi19_4.volkova_pi19_4.service;

import com.volkova_pi19_4.volkova_pi19_4.entity.Blog;
import com.volkova_pi19_4.volkova_pi19_4.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogService {
    @Autowired
    BlogRepository blogRepository;

    public void create(Blog blog) {blogRepository.save(blog);}

    public Optional<Blog> findById(Long id) {return blogRepository.findById(id);}

    public List<Blog> findAll() {return blogRepository.findAll();}

    //public void delete(){}

    //public void update(){}
}
