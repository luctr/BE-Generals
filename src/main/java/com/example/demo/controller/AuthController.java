package com.example.demo.controller;

import com.example.demo.dto.request.SignInForm;
import com.example.demo.dto.request.SignUpForm;
import com.example.demo.dto.response.JwtResponse;
import com.example.demo.dto.response.ResponseMessage;
import com.example.demo.model.Role;
import com.example.demo.model.RoleName;
import com.example.demo.model.User;
import com.example.demo.security.jwt.JwtProvider;
import com.example.demo.security.userPrinciple.UserPrinciple;
import com.example.demo.service.Role.RoleServiceImpl;
import com.example.demo.service.User.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@CrossOrigin("*")
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    UserServiceImpl userService;
    @Autowired
    RoleServiceImpl roleService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtProvider jwtProvider;
    @PostMapping("/signup")
    public ResponseEntity<ResponseMessage> register(@Valid @RequestBody SignUpForm signUpForm){
        if(userService.existsByUsername(signUpForm.getUsername())){
            return new ResponseEntity<>(new ResponseMessage("no user"), HttpStatus.OK);
        }
        User user = new User(signUpForm.getUsername(),passwordEncoder.encode(signUpForm.getPassword()),signUpForm.getPhoneNumber());
        Set<Role> roles = new HashSet<>();
        if (signUpForm.getRoles() == null){
            Role userRole = roleService.findByName(RoleName.USER).orElseThrow(()->new RuntimeException("ROLE NOT FOUND"));
            roles.add(userRole);
        }else {
            Set<String> strRoles = signUpForm.getRoles();
            strRoles.forEach(role ->{
                switch (role){
                    case "admin":
                        Role adminRole = roleService.findByName(RoleName.ADMIN).orElseThrow(()->
                                new RuntimeException("Role NOT FOUND"));
                        roles.add(adminRole);
                        break;
                    case "pm":
                        Role pmRole = roleService.findByName(RoleName.PM).orElseThrow(
                                ()-> new RuntimeException("Role NOT FOUND"));
                        roles.add(pmRole);
                        break;
                    default: Role userRole = roleService.findByName(RoleName.USER).orElseThrow(()->new RuntimeException("ROLE NOT FOUND"));
                        roles.add(userRole);
                }
            });
        }
        user.setRoles(roles);
        userService.save(user);
        return new ResponseEntity<>(new ResponseMessage("create success!!!"), HttpStatus.OK);
    }
    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> login(@Valid @RequestBody SignInForm signInForm) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInForm.getUsername(), signInForm.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.createToken(authentication);
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        return ResponseEntity.ok(new JwtResponse(token,userPrinciple.getUsername(), userPrinciple.getAuthorities()));
    }
}
