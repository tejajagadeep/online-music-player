package com.spotify.wishlistservice.repository;

import com.spotify.wishlistservice.model.Track;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackRepository extends MongoRepository<Track, String> {
}
