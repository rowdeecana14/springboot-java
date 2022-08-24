package com.example.demo.controllers;

import com.example.demo.entities.EmployeeEntity;
import com.example.demo.services.AuthService;
import com.example.demo.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1")
@Validated
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody EmployeeEntity request) {
        try{
//            return new ResponseEntity<>(authService.checkEmployeeLogin(request), HttpStatus.OK);
            return new ResponseEntity<>("OKAY", HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>("Failed!", HttpStatus.BAD_REQUEST);
        }
    }
}
