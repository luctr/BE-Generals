package com.example.demo.controller;

import com.example.demo.model.Generals;
import com.example.demo.model.Species;
import com.example.demo.service.Species.SpeciesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/species")
@CrossOrigin("*")
public class SpeciesController {
    @Autowired
    private SpeciesService speciesService;

    @GetMapping
    public ResponseEntity<Iterable<Species>> findAll(){
        Iterable<Species> findAll = speciesService.findAll();
        return new ResponseEntity<>(findAll, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Species> createNewSpecies(@RequestBody Species species) {
        speciesService.save(species);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Species> deleteById(@PathVariable Long id) {
        Optional<Species> speciesOptional = speciesService.findById(id);
        if (!speciesOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        speciesService.delete(id);
        return new ResponseEntity<>(speciesOptional.get(), HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Species> editSpecies(@PathVariable Long id, @RequestBody Species species) {
        Optional<Species> speciesOptional = speciesService.findById(id);
        if (!speciesOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        species.setId(id);
        speciesService.save(species);
        return new ResponseEntity<>( HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Species> findById(@PathVariable Long id) {
        Optional<Species> species = speciesService.findById(id);
        if (!species.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(species.get(), HttpStatus.OK);
    }
}
