package com.auth.authenticationservice.service.impl;

import com.auth.authenticationservice.entity.Role;
import com.auth.authenticationservice.entity.User;
import com.auth.authenticationservice.exceptiion.EmailAlreadyExists;
import com.auth.authenticationservice.exceptiion.InvalidSalaryException;
import com.auth.authenticationservice.exceptiion.UserNameAlreadyExists;
import com.auth.authenticationservice.payload.JwtResponse;
import com.auth.authenticationservice.payload.LoginDto;
import com.auth.authenticationservice.payload.RegisterDto;
import com.auth.authenticationservice.repository.RoleRepository;
import com.auth.authenticationservice.repository.UserRepository;
import com.auth.authenticationservice.security.JwtTokenProvider;
import com.auth.authenticationservice.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;


    @Override
    public User addUser(RegisterDto registerDto) {
        String email=registerDto.getEmail();
        String username=registerDto.getUsername();
        if(userRepository.existsByEmail(email)){
            throw new EmailAlreadyExists(String.format("%s already exists try with another",email));
        }
        if(userRepository.existsByUsername(username)){
            throw new UserNameAlreadyExists(String.format("%s already exists try with another",username));
        }

        User user =new User();
        user.setEmail(registerDto.getEmail());
        user.setContactNumber(registerDto.getContactNumber());
        user.setDateOfBirth(registerDto.getDateOfBirth());
        user.setFirstName(registerDto.getFirstName());
        user.setLastName(registerDto.getLastName());
        user.setPanCardNo(registerDto.getPanCardNo());
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setEmployerType(registerDto.getEmployerType());
        user.setSalaryPerMonth(registerDto.getSalaryPerMonth());
        double salary= registerDto.getSalaryPerMonth();
        double salaryPerYear=salary*12;
        if(salaryPerYear >= 100000 && salaryPerYear <= 500000){
            user.setUserType("A");
        }else if(salaryPerYear > 500000 && salaryPerYear <= 1000000){
            user.setUserType("B");
        }else if(salaryPerYear > 1000000 && salaryPerYear <= 1500000){
            user.setUserType("C");
        }else if(salaryPerYear > 1500000 && salaryPerYear <= 3000000){
            user.setUserType("D");
        }else if(salaryPerYear > 3000000){
            user.setUserType("E");
        }else{
            throw new InvalidSalaryException("Annual salary should be greater than 100000");
        }
        if(registerDto.getEmployerType().equalsIgnoreCase("salaried")){
            user.setEmployer(registerDto.getEmployer());
        }else{
            user.setEmployer("self");
        }
        Set<Role> roles=new HashSet<>();
        roles.add(roleRepository.findByName(registerDto.getRole()).get());
        log.info(roles.toString());
        user.setRoles(roles);
        return userRepository.save(user);
    }

    @Override
    public JwtResponse login(LoginDto loginDto) {

        Authentication authentication= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(),loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token= jwtTokenProvider.generateJwtToken(authentication);
        log.info(token);
        User user=userRepository.findByEmail(jwtTokenProvider.getUsername(token)).get();
        log.info(jwtTokenProvider.validateToken(token).toString());
        log.info(jwtTokenProvider.getUsername(token));
        JwtResponse jwtResponse=new JwtResponse();
        jwtResponse.setToken(token);
        jwtResponse.setUser(user);
        return jwtResponse;
    }

    @Override
    public List<User> fetchAllUsers() {
        return userRepository.findAll();
    }
}
