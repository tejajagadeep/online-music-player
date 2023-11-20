package com.spotify.musicservice.repository;

import com.example.sporifyservicedemo.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {
    UserDetails findByRefId(String refId);
}
