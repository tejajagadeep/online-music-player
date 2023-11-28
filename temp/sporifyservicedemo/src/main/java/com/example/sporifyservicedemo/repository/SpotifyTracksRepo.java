package com.example.sporifyservicedemo.repository;

import com.example.sporifyservicedemo.model.SpotifyTracks;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SpotifyTracksRepo extends MongoRepository<SpotifyTracks, Integer> {
}
