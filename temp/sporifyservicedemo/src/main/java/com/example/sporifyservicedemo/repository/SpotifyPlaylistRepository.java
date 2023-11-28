package com.example.sporifyservicedemo.repository;

import com.example.sporifyservicedemo.model.SpotifyPlaylist;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpotifyPlaylistRepository extends MongoRepository<SpotifyPlaylist, String> {
}
