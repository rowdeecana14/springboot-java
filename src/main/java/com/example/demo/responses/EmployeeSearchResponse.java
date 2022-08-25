package com.example.demo.responses;

import com.example.demo.entities.EmployeeEntity;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class EmployeeSearchResponse {
    private List<EmployeeEntity> employeeList;
    private Long numberOfItems;
    private int numberOfPages;
}
