package com.auth.authenticationservice.payload;

import com.auth.authenticationservice.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {

    private String token;

    private String type="Bearer";
    private User user;



}
