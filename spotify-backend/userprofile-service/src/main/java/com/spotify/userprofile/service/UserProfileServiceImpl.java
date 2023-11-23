package com.spotify.userprofile.service;

import com.spotify.userprofile.dto.UserProfileDto;
import com.spotify.userprofile.exception.ResourceAlreadyExistsException;
import com.spotify.userprofile.model.UserProfile;
import com.spotify.userprofile.repository.UserProfileRepository;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileRepository usersProfileRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public UserProfileServiceImpl(UserProfileRepository usersProfileRepository, ModelMapper modelMapper) {
        this.usersProfileRepository = usersProfileRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<UserProfileDto> getAllUsers() {

        return Stream.of(usersProfileRepository.findAll())
                .flatMap(entityList -> entityList.stream()
                        .map(entity -> modelMapper.map(entity, UserProfileDto.class))).toList();

    }

    @Override
    public UserProfileDto getUserProfileById(String username) {
        UserProfile entity = usersProfileRepository.findById(username)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Entity not found with ID: " + username));

        return modelMapper.map(entity, UserProfileDto.class);
    }

    @Override
    public UserProfileDto saveUserProfile(UserProfile userProfile) {


        if (usersProfileRepository.existsById(userProfile.getUsername())) {
            throw new ResourceAlreadyExistsException("Username Already exists");
        }

        if (usersProfileRepository.existsByEmail(userProfile.getEmail())) {
            throw new ResourceAlreadyExistsException("Email Already exists");
        }

        if (usersProfileRepository.existsByNumber(userProfile.getNumber())) {
            throw new ResourceAlreadyExistsException("Number Already exists");
        }

        userProfile.setRole("User");

        return modelMapper.map(usersProfileRepository.save(userProfile), UserProfileDto.class);
    }

    @Override
    public UserProfileDto updateUserProfile(UserProfileDto userProfileDto, String username) {
        UserProfile userProfile = usersProfileRepository.findById(username)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Entity not found with ID: " + username)
                       );
        if (usersProfileRepository.existsByEmail(userProfile.getEmail())) {
            throw new ResourceAlreadyExistsException("Email Already exists");
        }

        if (usersProfileRepository.existsByNumber(userProfile.getNumber())) {
            throw new ResourceAlreadyExistsException("Number Already exists");
        }
        userProfile.setEmail(userProfileDto.getEmail());
        userProfile.setFirstName(userProfileDto.getFirstName());
        userProfile.setLastName(userProfileDto.getLastName());
        userProfile.setNumber(userProfileDto.getNumber());
        userProfile.setDateOfBirth(userProfileDto.getDateOfBirth());

        usersProfileRepository.save(userProfile);

        return modelMapper.map(userProfile, UserProfileDto.class);
    }

}
