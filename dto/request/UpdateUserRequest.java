package com.company.booking.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateUserRequest(

        @NotBlank(message = "First name is required")
        @Size(max = 80, message = "First name must have maximum 80 characters")
        String fristName,


        @NotBlank(message = "Last name is required")
        @Size(max = 80, message = "Last name must have maximum 80 characters")
        String lastName,
       
        @NotBlank(message = "Email is required")
        @Email(message = "Email must be valid")
        @Size(max = 120, message = "Email must have maximum 120 characters")
        String email,
        Long companyId
){}