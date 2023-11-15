package com.spotify.userprofile.service;

import com.spotify.userprofile.dto.UserProfileDto;
import com.spotify.userprofile.model.UserProfile;

import java.util.List;

public interface UserProfileService {
    List<UserProfileDto> getAllUsers();

    UserProfileDto getUserProfileById(long id);

    UserProfileDto saveUserProfile(UserProfile userProfile);

    UserProfileDto updateUserProfile(UserProfileDto userProfileDto, long id);
}
