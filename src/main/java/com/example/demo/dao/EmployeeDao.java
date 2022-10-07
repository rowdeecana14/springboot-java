package com.example.demo.dao;

import com.example.demo.entities.EmployeeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeDao extends JpaRepository<EmployeeEntity, Long> {
    @Query(
        value = "SELECT * FROM employees AS e " +
                "WHERE " +
                "e.first_name like %:search% OR e.middle_name like %:search% OR e.last_name like %:search% " +
                "OR e.email like %:search% OR e.mobile like %:search% OR e.position like %:search%",
        nativeQuery = true
    )
    Page<EmployeeEntity> getEmployeeList(String search, Pageable pageable);

//    Page<EmployeeEntity> getSearchEmployeeList(String firstName, Pageable pageable);
//    @Query("SELECT * FROM employees e WHERE e.email =:email")
//    ArrayList checkEmployeeLogin(String email);
}
