package com.auth.authenticationservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    @Column(nullable = false)
    private long contactNumber;
    @Column(nullable = false,unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    private double salaryPerMonth;
    private String panCardNo;
    private String employerType;
    private String employer;
    private String userType;
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles",joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"),inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName = "id"))
    private Set<Role> roles;
}
