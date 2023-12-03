package com.spotify.userprofile.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileDto {

        private String username;
        private String firstName;
        private String lastName;
        private String password;
        private Date dateOfBirth;

        private String email;

    }

