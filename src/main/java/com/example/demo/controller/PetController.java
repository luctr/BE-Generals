package com.example.demo.controller;

import com.example.demo.model.Pet;
import com.example.demo.service.Pet.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/pets")
public class PetController {

    @Autowired
    private PetService generlsService;

    @GetMapping
    public ResponseEntity<Iterable<Pet>> findAll() {
        Iterable<Pet> generals = generlsService.findAll();
        return new ResponseEntity<>(generals, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Pet> createNewGenerals(@RequestBody Pet pet) {
        generlsService.save(pet);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Pet> deleteById(@PathVariable Long id) {
        Optional<Pet> generalsOptional = generlsService.findById(id);
        if (!generalsOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        generlsService.delete(id);
        return new ResponseEntity<>(generalsOptional.get(), HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pet> editGenerals(@PathVariable Long id, @RequestBody Pet pet) {
        Optional<Pet> cityOptional = generlsService.findById(id);
        if (!cityOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        pet.setId(id);
        generlsService.save(pet);
        return new ResponseEntity<>( HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pet> findById(@PathVariable Long id) {
        Optional<Pet> generalsOptional = generlsService.findById(id);
        if (!generalsOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(generalsOptional.get(), HttpStatus.OK);
    }
}
