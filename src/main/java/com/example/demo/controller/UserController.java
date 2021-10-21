package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.User.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
    @Autowired
    private IUserService userService;
//    @GetMapping
//    public ResponseEntity<Iterable<User>> findAll(){
//        Iterable<User> userIterable= userService.findAll();
//        return new ResponseEntity<>(userIterable, HttpStatus.OK);
//    }
//    @GetMapping("/{id}")
//    public ResponseEntity<User> findById(@PathVariable Long id) {
//        Optional<User> userOptional = userService.findById(id);
//        if (!userOptional.isPresent()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(userOptional.get(), HttpStatus.OK);
//    }
    @GetMapping("/search/{name}")
    public ResponseEntity<User> findByName(@PathVariable String name) {
        Optional<User> userOptional = userService.findByUsername(name);
        if (!userOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userOptional.get(), HttpStatus.OK);
    }

//    @PostMapping
//    public ResponseEntity<User> save(@RequestBody User user) {
//        userService.save(user);
//        return new ResponseEntity<>(HttpStatus.CREATED);
    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<User> editUser(@PathVariable Long id, @RequestBody User user){
//        Optional<User> userOptional = userService.findById(id);
//        if(!userOptional.isPresent()){
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        if(user.getUsername().trim().equals("")){
//            user.setUsername(userOptional.get().getUsername());
//        }
//        user.setId(id);
//        userService.save(user);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<User> deleteById(@PathVariable Long id){
//        Optional<User> userOptional = userService.findById(id);
//        if(!userOptional.isPresent()){
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        userService.delete(id);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//}
