package com.example.sporifyservicedemo.repository;

import com.example.sporifyservicedemo.model.Track;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TrackRepo extends MongoRepository<Track, String> {
}
