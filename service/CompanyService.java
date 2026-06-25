package com.company.booking.service;

import com.company.booking.dto.request.CreateCompanyRequest;
import com.company.booking.dto.response.CompanyResponse;
import com.company.booking.entity.Company;
import com.company.booking.exception.BusinessException;
import com.company.booking.exception.ResourceNotFoundException;
import com.company.booking.mapper.CompanyMapper;
import com.company.booking.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    public CompanyResponse create(CreateCompanyRequest request) {

        if (companyRepository.existsByName(request.name())) {
            throw new BusinessException("Company name must be unique");
        }

        Company company = new Company();
        company.setName(request.name());
        company.setCountry(request.country());

        Company saved = companyRepository.save(company);
        return companyMapper.toResponse(saved);
    }

    public CompanyResponse getById(Long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Company with id " + id + " was not found"));

        return companyMapper.toResponse(company);
    }

    public List<CompanyResponse> getAll() {
        return companyRepository.findAll()
                .stream()
                .map(companyMapper::toResponse)
                .toList();
    }
}