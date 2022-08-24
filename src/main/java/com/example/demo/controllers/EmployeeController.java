package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.entities.EmployeeEntity;
import com.example.demo.services.EmployeeService;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/v1")
@Validated
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public ResponseEntity<?> getEmployeeList() {
        try{
            return new ResponseEntity<>(employeeService.getEmployeeList(), HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>("Failed!", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<?> getEmployeeDetails(@PathVariable(value="id") long id) {
        try{
            return new ResponseEntity<>(employeeService.getEmployeeDetails(id), HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>("Failed!", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/employees")
    public ResponseEntity<?> createEmployee(@Valid @RequestBody EmployeeEntity data) {
        try{
            return new ResponseEntity<>(employeeService.createEmployee(data), HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>("Failed!", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable Long id, @RequestBody EmployeeEntity data) {
        try{
            return new ResponseEntity<>(employeeService.updateEmployee(id, data), HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>("Failed!", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity deleteEmployee(@PathVariable(value="id") long id) {
        try{
            employeeService.deleteEmployee(id);
            return new ResponseEntity("Success!", HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>("Failed!", HttpStatus.BAD_REQUEST);
        }
    }
}
