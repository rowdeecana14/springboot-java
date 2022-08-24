package com.example.demo.services;

import com.example.demo.helpers.CipherHelper;
import com.example.demo.dao.EmployeeDao;
import com.example.demo.entities.EmployeeEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeDao employeeDao;

    private CipherHelper cipherHelper = new CipherHelper();

    @Value("${spring.cipher.algorithm}")
    private String algorithm;

    public ArrayList<EmployeeEntity> getEmployeeList() {
        return (ArrayList<EmployeeEntity>) employeeDao.findAll();
    }

    public EmployeeEntity getEmployeeDetails(long id) {
        return employeeDao.findById(id).orElse(null);
    }

    public EmployeeEntity createEmployee(EmployeeEntity data) {
        try {
            String password = encryptPassword(data.getPassword());
            data.setPassword(password);

            return employeeDao.save(data);
        }
        catch(Exception ex){
            return null;
        }
    }
    public EmployeeEntity updateEmployee(long id, EmployeeEntity data) {
        Optional<EmployeeEntity> employeeFound = employeeDao.findById(id);

        if (employeeFound.isPresent()) {
            EmployeeEntity employeeUpdate = employeeFound.get();
            employeeUpdate.setFirstName(data.getFirstName());
            employeeUpdate.setMiddleName(data.getMiddleName());
            employeeUpdate.setLastName(data.getLastName());
            employeeUpdate.setEmail(data.getEmail());

            return employeeDao.save(data);
        }
        else {
            return null;
        }
    }

    public Boolean deleteEmployee(long id) {
        try {
            employeeDao.deleteById(id);
            return true;
        }
        catch (Exception ex) {
            return  false;
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
