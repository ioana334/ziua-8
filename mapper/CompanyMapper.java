package com.company.booking.mapper;

import com.company.booking.dto.response.CompanyResponse;
import com.company.booking.entity.Company;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper {

    public CompanyResponse toResponse(Company company) {
        return new CompanyResponse(
                company.getId(),
                company.getName(),
                company.getCountry()
        );
    }
}