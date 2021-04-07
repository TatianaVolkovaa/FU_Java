package com.Tatiana_Volkova_PI19_4.Tatiana_Volkova_PI19_4.services;

import com.Tatiana_Volkova_PI19_4.Tatiana_Volkova_PI19_4.entities.Category;
import com.Tatiana_Volkova_PI19_4.Tatiana_Volkova_PI19_4.repos.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public void create(Category category){
        categoryRepository.save(category);
    }

    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

    public Optional<Category> findById(Long id){
        return categoryRepository.findById(id);
    }

    public boolean update(Category category, Long id) {
        if (findById(id).isPresent()) {
            category.setId(id);
            categoryRepository.save(category);
            return true;
        }
        return false;
    }

    public boolean delete(Long id) {
        if (findById(id).isPresent()) {
            categoryRepository.deleteById(id);
        }
        return false;
    }
}
