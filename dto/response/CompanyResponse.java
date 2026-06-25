package com.company.booking.dto.response;

public record CreateCompanyResponse(
    Long id;
    String name;
    String country;
){}