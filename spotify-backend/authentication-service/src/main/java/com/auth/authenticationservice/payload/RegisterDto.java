package com.shalem.authenticationservice.payload;

import com.shalem.authenticationservice.entity.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {

    private String username;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;

    private long contactNumber;

    private String email;

    private String password;
    private double salaryPerMonth;
    private String panCardNo;
    private String employerType;
    private String employer;
    private String role;
}
