package com.example.demo.dao;

import com.example.demo.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface EmployeeDao extends JpaRepository<EmployeeEntity, Long> {

//    @Query("SELECT * FROM employees e WHERE e.email =:email")
//    ArrayList checkEmployeeLogin(String email);
}
