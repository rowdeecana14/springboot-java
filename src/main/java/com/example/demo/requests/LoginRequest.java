package com.example.demo.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class LoginRequest {
    @NotEmpty(message = "Email must not empty")
    private String email;

    @NotEmpty(message = "Password must not empty")
    private String password;
}
