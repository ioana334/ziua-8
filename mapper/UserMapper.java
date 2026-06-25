package com.company.booking.mapper;

import com.company.booking.dto.response.UserResponse;
import com.company.booking.entity.AppUser;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserMapper toResponse(AppUser user) {
        Long companyId=user.getCompany() != null ? user.getCompany().getId() : null;

        return new UserResponse(
                user.getId(),
                user.getFristName(),
                user.getLastName(),
                user.getEmail(,)
                companyId
                
        );
    }
}