package com.example.demo.services;

import com.example.demo.helpers.CipherHelper;
import com.example.demo.dao.EmployeeDao;
import com.example.demo.entities.EmployeeEntity;

import com.example.demo.requests.CreateEmployeeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.validation.Valid;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.*;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeDao employeeDao;

    private CipherHelper cipherHelper = new CipherHelper();

    @Value("${spring.cipher.algorithm}")
    private String algorithm;

    public Page<EmployeeEntity> getEmployeeList(String search, Pageable pageable) {
        try {
            return employeeDao.getEmployeeList(search, pageable);
        }
        catch(Exception ex){
            return null;
        }
    }

    public EmployeeEntity getEmployeeDetails(long id) {
        try {
            return employeeDao.findById(id).orElse(null);
        }
        catch(Exception ex){
            return null;
        }
    }

    public EmployeeEntity createEmployee(EmployeeEntity request) {
        try {
            String password = encryptPassword(request.getPassword());
            request.setPassword(password);

            return employeeDao.save(request);
        }
        catch(Exception ex){
            return null;
        }
    }
    public EmployeeEntity updateEmployee(long id, EmployeeEntity request) {
        try {
            Optional<EmployeeEntity> employeeFound = employeeDao.findById(id);

            if (employeeFound.isPresent()) {
                EmployeeEntity employeeUpdate = employeeFound.get();
                employeeUpdate.setFirstName(request.getFirstName());
                employeeUpdate.setMiddleName(request.getMiddleName());
                employeeUpdate.setLastName(request.getLastName());
                employeeUpdate.setMobile(request.getMobile());
                employeeUpdate.setEmployeeStatus(request.getEmployeeStatus());
                employeeUpdate.setStatus(request.getStatus());

                return employeeDao.save(employeeUpdate);
            }
            else {
                return null;
            }
        }
         catch (Exception ex) {
            return null;
        }
    }

    public EmployeeEntity deleteEmployee(long id) {
        try {
            Optional<EmployeeEntity> employeeFound = employeeDao.findById(id);

            if (employeeFound.isPresent()) {
                employeeDao.deleteById(id);

                return employeeFound.get();
            }
            else {
                return null;
            }
        }
        catch (Exception ex) {
            return null;
        }
    }

    public String encryptPassword(String text) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        IvParameterSpec iv = cipherHelper.generateIv(algorithm);
        return cipherHelper.encrypt(text,iv,algorithm);
    }

    public String decryptPassword(String text) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, InvalidKeySpecException, InvalidKeyException {
        IvParameterSpec iv = cipherHelper.generateIv(algorithm);
        return cipherHelper.decrypt(text,iv,algorithm);
    }
}
