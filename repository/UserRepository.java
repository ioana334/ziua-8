package com.company.booking.repository;

import com.company.booking.entity.AppUser;
import org.springframework.data.jpa.repository. JpaRepository;

public interface UsserRepository extends JpaRepository<AppUser,Long>{
    boolean existsByEmail(String email);
}