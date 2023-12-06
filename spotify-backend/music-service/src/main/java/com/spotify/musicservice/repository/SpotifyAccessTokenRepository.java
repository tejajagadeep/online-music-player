package com.spotify.musicservice.repository;

import com.spotify.musicservice.model.SpotifyAccessToken;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpotifyAccessTokenRepository extends MongoRepository<SpotifyAccessToken, Integer> {
}
