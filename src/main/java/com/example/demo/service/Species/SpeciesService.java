package com.example.demo.service.Species;

import com.example.demo.model.Category;
import com.example.demo.repository.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class SpeciesService implements ISpeciesService{

    @Autowired
    private CategoriesRepository categoriesRepository;

    @Override
    public Iterable<Category> findAll() {
        return categoriesRepository.findAll();
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoriesRepository.findById(id);
    }

    @Override
    public List<Category> findByName(String name) {
        return null;
    }

    @Override
    public void save(Category category) {
        categoriesRepository.save(category);
    }

    @Override
    public void delete(Long id) {
        categoriesRepository.deleteById(id);
    }
}
