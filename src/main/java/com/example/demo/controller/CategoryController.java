package com.example.demo.controller;

import com.example.demo.model.Category;
import com.example.demo.service.Species.SpeciesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
    @RequestMapping("/categories")
@CrossOrigin("*")
public class CategoryController {
    @Autowired
    private SpeciesService speciesService;

    @GetMapping
    public ResponseEntity<Iterable<Category>> findAll(){
        Iterable<Category> findAll = speciesService.findAll();
        return new ResponseEntity<>(findAll, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Category> createNewSpecies(@RequestBody Category category) {
        speciesService.save(category);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Category> deleteById(@PathVariable Long id) {
        Optional<Category> speciesOptional = speciesService.findById(id);
        if (!speciesOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        speciesService.delete(id);
        return new ResponseEntity<>(speciesOptional.get(), HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> editSpecies(@PathVariable Long id, @RequestBody Category category) {
        Optional<Category> speciesOptional = speciesService.findById(id);
        if (!speciesOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        category.setId(id);
        speciesService.save(category);
        return new ResponseEntity<>( HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id) {
        Optional<Category> species = speciesService.findById(id);
        if (!species.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(species.get(), HttpStatus.OK);
    }
}
