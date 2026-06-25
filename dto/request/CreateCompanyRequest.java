package com.company.booking.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateCompanyRequest(

        @NotBlank(message = "Company name is required")
        @Size(max = 150, message = "Company name must have maximum 150 characters")
        String name,

        @NotBlank(message = "Country is required")
        String country
) {}