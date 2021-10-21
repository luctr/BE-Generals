package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.service.Pet.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private PetService petService;

    @GetMapping
    public ResponseEntity<Iterable<Product>> findAll() {
        Iterable<Product> petIterable = petService.findAll();
        return new ResponseEntity<>(petIterable, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> createNewPet(@RequestBody Product product) {
        petService.save(product);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteById(@PathVariable Long id) {
        Optional<Product> petOptional = petService.findById(id);
        if (!petOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        petService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/edit")
    public ResponseEntity<Product> editPet(@RequestBody Product product) {
        Optional<Product> petOptional = petService.findById(product.getId());
        if (!petOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        petService.save(product);
        return new ResponseEntity<>( HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id) {
        Optional<Product> petOptional = petService.findById(id);
        if (!petOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(petOptional.get(), HttpStatus.OK);
    }

//    @GetMapping("/search")
//    public ResponseEntity<List<Pet>> findByName(@RequestParam("name") String name) {
//        List<Pet> petOptional = petService.findByName("%"+name+"%");
//        return new ResponseEntity<>(petOptional, HttpStatus.OK);
//    }
    @GetMapping("/search/{name}")
    public ResponseEntity<List<Product>> findByName(@PathVariable String name) {
        List<Product> productOptional = petService.findByName("%"+name+"%");
        return new ResponseEntity<>(productOptional, HttpStatus.OK);
    }
}
