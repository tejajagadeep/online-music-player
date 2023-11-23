package com.spotify.userprofile.service;



import com.spotify.userprofile.dto.UserDto;
import com.spotify.userprofile.model.UserProfile;

import java.util.List;

public interface UserProfileService {
    List<UserDto> getAllUsers();

    UserDto getUserProfileById(String username);

    UserDto saveUserProfile(UserProfile userProfile);

    UserDto updateUserProfile(UserDto userProfileDto, String username);

}
