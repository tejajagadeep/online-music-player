package com.example.sporifyservicedemo.service;

import com.example.sporifyservicedemo.configuration.SpotifyConfiguration;
import com.example.sporifyservicedemo.model.SpotifyPlaylist;
import com.example.sporifyservicedemo.model.SpotifyTracks;
import com.example.sporifyservicedemo.model.Track;
import com.example.sporifyservicedemo.repository.SpotifyPlaylistRepository;
import com.example.sporifyservicedemo.repository.SpotifyTracksRepo;
import com.example.sporifyservicedemo.repository.TrackRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SpotifyServiceImpl implements SpotifyService {

    private final SpotifyConfiguration spotifyConfiguration;
    private final SpotifyPlaylistRepository spotifyPlaylistRepository;
    private final SpotifyTracksRepo spotifyTracksRepo;

    private final TrackRepo trackRepo;

    @Autowired
    public SpotifyServiceImpl(SpotifyConfiguration spotifyConfiguration, SpotifyPlaylistRepository spotifyPlaylistRepository, SpotifyTracksRepo spotifyTracksRepo, TrackRepo trackRepo) {
        this.spotifyConfiguration = spotifyConfiguration;
        this.spotifyPlaylistRepository = spotifyPlaylistRepository;
        this.spotifyTracksRepo = spotifyTracksRepo;
        this.trackRepo = trackRepo;
    }

    @Override
    public SpotifyPlaylist getBillBoard100Playlist() {
        return spotifyPlaylistRepository.findById("6UeSakyzhiEt4NB3UAd6NQ").get();
    }

    @Override
    public SpotifyPlaylist saveSpotifyPlaylist(SpotifyPlaylist spotifyPlaylist) {
        return spotifyPlaylistRepository.save(spotifyPlaylist);
    }

    @Override
    public SpotifyPlaylist getDiscoverWeeklyPlaylist() {
        return spotifyPlaylistRepository.findById("1mjrbWPCpQdNcohvou99aJ").get();
    }

    @Override
    public SpotifyPlaylist getTodayTopHitsPlaylist() {
        return spotifyPlaylistRepository.findById("37i9dQZF1DXcBWIGoYBM5M").get();
    }

    @Override
    public SpotifyTracks saveTracks(SpotifyTracks spotifyTracks) {
        spotifyTracks.setId(1);
        return spotifyTracksRepo.save(spotifyTracks);
    }

    @Override
    public SpotifyTracks search(String query) {
        return spotifyTracksRepo.findById(1).get();
    }

    @Override
    public Track saveTrack(Track track) {
        return trackRepo.save(track);
    }

    @Override
    public Track getTrack(String trackId) {
        return trackRepo.findById("3hhbDnFUb2bicI2df6VurK").get();
    }
}