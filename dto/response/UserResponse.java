package com.company.booking.dto.response;

public record UserResponse(
    Long id;
    String fristName;
    String lastName;
    String email;
    Long companyId 
){}