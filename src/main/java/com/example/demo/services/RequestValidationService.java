package com.example.demo.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

public class RequestValidationService {
    public ResponseEntity<?> validationError(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {

            Map<String, String> map = new HashMap<>();
            for(FieldError error: bindingResult.getFieldErrors()) {
                map.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<Map<String, String>>(map, HttpStatus.BAD_REQUEST);
        }

        return null;
    }
}
