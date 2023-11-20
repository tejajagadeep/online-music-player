package com.spotify.musicservice.repository;

import com.spotify.musicservice.model.UserDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepository extends MongoRepository<UserDetails, Long> {
    UserDetails findByRefId(String refId);
}
