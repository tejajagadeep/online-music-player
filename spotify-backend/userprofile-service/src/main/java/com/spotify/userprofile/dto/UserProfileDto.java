package com.spotify.userprofile.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserProfileDto {
    private long id;

    private String username;
    private String firstName;
    private String lastName;
    private long number;
    private Date dateOfBirth;

    private String email;

}
