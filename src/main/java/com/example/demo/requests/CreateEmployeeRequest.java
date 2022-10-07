package com.example.demo.requests;

import com.example.demo.entities.EmployeeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class CreateEmployeeRequest {
    @NotEmpty(message = "The first name is required.")
    @Size(min = 3, max = 100, message = "The length of first name must be between 3 and 100 characters.")
    private String firstName;

    @NotEmpty(message = "The middle name is required.")
    @Size(min = 3, max = 100, message = "The length of middle name must be between 3 and 100 characters.")
    private String middleName;

    @NotEmpty(message = "The last name is required.")
    @Size(min = 3, max = 100, message = "The length of last name must be between 3 and 100 characters.")
    private String lastName;

    @NotEmpty(message = "The email is required.")
    private String email;

    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
