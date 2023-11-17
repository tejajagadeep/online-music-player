package com.spotify.userprofile.service;

import com.spotify.userprofile.dto.UserProfileDto;
import com.spotify.userprofile.exception.ResourceAlreadyExistsException;
import com.spotify.userprofile.model.UserProfile;
import com.spotify.userprofile.exception.ResourceNotFoundException;
import com.spotify.userprofile.repository.UserProfileRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    public UserProfileDto getUserProfileById(long id) {
        UserProfile entity = usersProfileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found with ID: " + id));

        return modelMapper.map(entity, UserProfileDto.class);
    }

    @Override
    public UserProfileDto saveUserProfile(UserProfile userProfile) {

        if (usersProfileRepository.existsByUsername(userProfile.getUsername()) || usersProfileRepository.existsByEmail(userProfile.getEmail())){
            throw new ResourceAlreadyExistsException("UserProfile resource already exists");
        }

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        userProfile.setPassword(bCryptPasswordEncoder.encode(userProfile.getPassword()));
        return modelMapper.map(usersProfileRepository.save(userProfile), UserProfileDto.class);
    }

    @Override
    public UserProfileDto updateUserProfile(UserProfileDto userProfileDto, long id) {
        UserProfile entity = usersProfileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found with ID: " + id));

        entity.setEmail(userProfileDto.getEmail());
        entity.setFirstName(userProfileDto.getFirstName());
        entity.setLastName(userProfileDto.getLastName());
        entity.setNumber(userProfileDto.getNumber());
        entity.setDateOfBirth(userProfileDto.getDateOfBirth());

        usersProfileRepository.save(entity);

        return modelMapper.map(entity, UserProfileDto.class);
    }


}
