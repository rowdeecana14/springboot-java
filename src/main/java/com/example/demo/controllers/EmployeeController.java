package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.entities.EmployeeEntity;
import com.example.demo.services.EmployeeService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/v1")
@Validated
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public ResponseEntity<?> getEmployeeList(
            @RequestParam(defaultValue = "") String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String orderCol,
            @RequestParam(defaultValue = "asc") String orderDir
        ) {

        try {
            Sort.Direction direction = orderDir.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
            Pageable pageable = PageRequest.of(page - 1, size, Sort.by(direction, orderCol));
            Page<EmployeeEntity> pageEmployees = employeeService.getEmployeeList(search, pageable);
            Map<String, Object> response = new HashMap<String, Object>() {{
                put("message", "Employee was successfully fetched.");
                put("currentPage", pageEmployees.getNumber() + 1);
                put("totalItems", pageEmployees.getTotalElements());
                put("totalPages", pageEmployees.getTotalPages());
                put("data", pageEmployees.getContent());
            }};

            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch (Exception ex){
            Map<String, Object> response  = new HashMap<String, Object>() {{
                put("message", ex.getMessage());
            }};
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<?> getEmployeeDetails(@PathVariable(value="id") long id) {
        try{
            EmployeeEntity employee = employeeService.getEmployeeDetails(id);
            Map<String, Object> response  = new HashMap<String, Object>() {{
                put("message", employee != null ? "Employee was successfully fetched." : "Employee not found.");
                put("data", employee);
            }};

            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch (Exception ex){
            Map<String, Object> response  = new HashMap<String, Object>() {{
                put("message", ex.getMessage());
            }};
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/employees")
    public ResponseEntity<?> createEmployee(@Valid @RequestBody EmployeeEntity request) {
        try{
            EmployeeEntity employee = employeeService.createEmployee(request);
            Map<String, Object> response  = new HashMap<String, Object>() {{
                put("message", employee != null ? "Employee was successfully saved." : "Employee record not saved.");
                put("data", employee);
            }};

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        catch (Exception ex){
            Map<String, Object> response  = new HashMap<String, Object>() {{
                put("message", ex.getMessage());
            }};
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable Long id, @RequestBody EmployeeEntity data) {
        try{
            EmployeeEntity employee = employeeService.updateEmployee(id, data);
            Map<String, Object> response  = new HashMap<String, Object>() {{
                put("message", employee != null ? "Employee was successfully updated." : "Employee record not updated.");
                put("data", employee);
            }};
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch (Exception ex){
            Map<String, Object> response  = new HashMap<String, Object>() {{
                put("message", ex.getMessage());
            }};
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity deleteEmployee(@PathVariable(value="id") long id) {
        try{
            EmployeeEntity employee = employeeService.deleteEmployee(id);
            Map<String, Object> response  = new HashMap<String, Object>() {{
                put("message", employee != null ? "Employee was successfully deleted." : "Employee record not found.");
                put("data", employee);
            }};

            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch (Exception ex){
            Map<String, Object> response  = new HashMap<String, Object>() {{
                put("message", ex.getMessage());
            }};
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
