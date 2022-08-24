package com.example.demo.services;

import com.example.demo.dao.EmployeeDao;
import com.example.demo.entities.EmployeeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AuthService {
    @Autowired
    private EmployeeDao employeeDao;

    public ArrayList checkEmployeeLogin(EmployeeEntity request) {
        try {
//            return employeeDao.checkEmployeeLogin(request.getEmail());
            return  null;
        }
        catch(Exception ex){
            return null;
        }
    }
}
