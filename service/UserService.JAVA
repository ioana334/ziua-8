package com.company.booking.service;

import com.company.booking.dto.request.CreateUserRequest;
import com.company.booking.dto.request.UpdateUserRequest;
import com.company.booking.dto.response.UserResponse;
import com.company.booking.entity.AppUser;
import com.company.booking.entity.Company;
import com.company.booking.exception.BusinessException;
import com.company.booking.exception.ResourceNotFoundException;
import com.company.booking.mapper.UserMapper;
import com.company.booking.repository.CompanyRepository;
import com.company.booking.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
    private final UserMapper userMapper;

    public UserResponse create(CreateUserRequest request) {

        if (userRepository.existsByEmail(request.email())) {
            throw new BusinessException("Email must be unique");
        }

        Company company = null;
        if (request.companyId() != null) {
            company = companyRepository.findById(request.companyId())
                    .orElseThrow(() -> new ResourceNotFoundException("Company not found"));
        }

        AppUser user = new AppUser();
        user.setFirstName(request.firstName());
        user.setLastName(request.lastName());
        user.setEmail(request.email());
        user.setCompany(company);

        AppUser saved = userRepository.save(user);
        return userMapper.toResponse(saved);
    }

    public UserResponse update(Long id, UpdateUserRequest request) {

        AppUser user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (!user.getEmail().equals(request.email()) && userRepository.existsByEmail(request.email())) {
            throw new BusinessException("Email must be unique");
        }

        Company company = null;
        if (request.companyId() != null) {
            company = companyRepository.findById(request.companyId())
                    .orElseThrow(() -> new ResourceNotFoundException("Company not found"));
        }

        user.setFirstName(request.firstName());
        user.setLastName(request.lastName());
        user.setEmail(request.email());
        user.setCompany(company);

        AppUser updated = userRepository.save(user);
        return userMapper.toResponse(updated);
    }

    public UserResponse getById(Long id) {
        AppUser user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return userMapper.toResponse(user);
    }

    public List<UserResponse> getAll() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toResponse)
                .toList();
    }

    public void delete(Long id) {
        AppUser user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        userRepository.delete(user);
    }
}