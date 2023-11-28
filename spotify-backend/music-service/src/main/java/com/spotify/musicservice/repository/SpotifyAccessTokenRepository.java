package com.spotify.musicservice.repository;

import com.spotify.musicservice.model.SpotifyAccessToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpotifyAccessTokenRepository extends JpaRepository<SpotifyAccessToken, Integer> {
}
