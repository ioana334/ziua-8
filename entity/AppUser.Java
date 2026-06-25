package com.company.booking.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "app_user")
@Getter
@Setter
@NoArgsConstructor
public class AppUser{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 80)
    private String fristname;
    
    @Column(nullable = false, length = 80)
    private String lastname;

    @Column(nullable = false, unique=true,lenght= 120)
    private String email;

    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColum(name="cmpany_id")
    private Company company;
}