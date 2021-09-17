package com.example.demo.controller;

import com.example.demo.model.Generals;
import com.example.demo.service.Generls.GenerlsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/generals")
public class GeneralsController {

    @Autowired
    private GenerlsService generlsService;

    @GetMapping
    public ResponseEntity<Iterable<Generals>> findAll() {
        Iterable<Generals> generals = generlsService.findAll();
        return new ResponseEntity<>(generals, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Generals> createNewGenerals(@RequestBody Generals generals) {
        generlsService.save(generals);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Generals> deleteById(@PathVariable Long id) {
        Optional<Generals> generalsOptional = generlsService.findById(id);
        if (!generalsOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        generlsService.delete(id);
        return new ResponseEntity<>(generalsOptional.get(), HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Generals> editGenerals(@PathVariable Long id, @RequestBody Generals generals) {
        Optional<Generals> cityOptional = generlsService.findById(id);
        if (!cityOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        generals.setId(id);
        generlsService.save(generals);
        return new ResponseEntity<>( HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Generals> findById(@PathVariable Long id) {
        Optional<Generals> generalsOptional = generlsService.findById(id);
        if (!generalsOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(generalsOptional.get(), HttpStatus.OK);
    }
}
