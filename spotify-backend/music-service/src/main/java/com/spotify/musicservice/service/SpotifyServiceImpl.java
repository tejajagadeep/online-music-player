package com.spotify.musicservice.service;

import com.spotify.musicservice.dto.*;
import com.spotify.musicservice.exception.ResourceNotFoundException;
import com.spotify.musicservice.model.SpotifyAccessToken;
import com.spotify.musicservice.repository.SpotifyAccessTokenRepository;
import io.micrometer.observation.annotation.Observed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Base64;

@Service
@Slf4j
@Observed(name = "spotify.service.impl")
public class SpotifyServiceImpl implements SpotifyService{

    private final SpotifyAccessTokenRepository spotifyAccessTokenRepository;

    private final RestTemplate restTemplate;
    @Value("${spotify.account.url}")
    String accountApiUrl;

    @Value("${spotify.api.url}")
    String apiUrl;
    @Value("${spotify.client.id}")
    String clientId;

    @Value("${spotify.client.secret}")
    String clientSecret;

    @Autowired
    public SpotifyServiceImpl(SpotifyAccessTokenRepository spotifyAccessTokenRepository, RestTemplate restTemplate) {
        this.spotifyAccessTokenRepository = spotifyAccessTokenRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    @Scheduled(fixedRate = 3599000)
    @Observed(name = "get.spotify.access.token")
    public String getSpotifyAccessToken() {

        // Make the request
        String authHeader = "Basic " + Base64.getEncoder().encodeToString((clientId + ":" + clientSecret).getBytes());

        // Set up headers for the HTTP request
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authHeader);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // Set up the request body with the grant type
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "client_credentials");

        // Create the HTTP request entity
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(body, headers);

        // Make a POST request to the Spotify token endpoint to obtain the access token
        ResponseEntity<SpotifyAccessToken> responseEntity = restTemplate.postForEntity(accountApiUrl + "/token", requestEntity, SpotifyAccessToken.class);
        SpotifyAccessToken spotifyAccessToken = responseEntity.getBody();
        log.trace("Inside SpotifyServiceImpl getSpotifyAccessToken");
        if (spotifyAccessToken != null) {
            spotifyAccessToken.setId(1);
            spotifyAccessTokenRepository.save(spotifyAccessToken);
            log.info("saved: " +spotifyAccessToken);
            return "Access Token Saved";
        } else {
            log.error("Resource not not found. returned null");
            throw new ResourceNotFoundException("Resource Not Found");
        }
    }

    @Override
    @Observed(name = "get.bill.board.100.playlist")
    public SpotifyPlaylist getBillBoard100Playlist() {
        String playlistId="6UeSakyzhiEt4NB3UAd6NQ";
        RequestEntity<Void> requestEntity = playListRequest(playlistId);
        log.trace("Inside SpotifyServiceImpl getBillBoard100Playlist");
        return restTemplate.exchange(requestEntity, SpotifyPlaylist.class).getBody();
    }
    @Override
    @Observed(name = "get.today.top.hits.playlist")
    public SpotifyPlaylist getTodayTopHitsPlaylist() {
        String playlistId="37i9dQZF1DXcBWIGoYBM5M";
        log.trace("Inside SpotifyServiceImpl getTodayTopHitsPlaylist");
        RequestEntity<Void> requestEntity = playListRequest(playlistId);
        return restTemplate.exchange(requestEntity, SpotifyPlaylist.class).getBody();
    }

    @Override
    @Observed(name = "get.discover.weekly.playlist")
    public SpotifyPlaylist getDiscoverWeeklyPlaylist() {
        String playlistId="1mjrbWPCpQdNcohvou99aJ";
        log.trace("Inside SpotifyServiceImpl getDiscoverWeeklyPlaylist");
        RequestEntity<Void> requestEntity = playListRequest(playlistId);
        return restTemplate.exchange(requestEntity, SpotifyPlaylist.class).getBody();
    }

    @Override
    public SpotifyPlaylist getPlaylist(String playlistId) {
        RequestEntity<Void> requestEntity = playListRequest(playlistId);
        return restTemplate.exchange(requestEntity, SpotifyPlaylist.class).getBody();
    }

    @Override
    @Observed(name = "get.track")
    public Track getTrack(String trackId) {
        log.trace("Inside SpotifyServiceImpl getTrack");
        log.info("get Track: "+trackId);
        RequestEntity<Void> requestEntity = trackRequest(trackId);
        return restTemplate.exchange(requestEntity, Track.class).getBody();
    }

    @Override
    public Album getAlbum(String albumId) {
        HttpHeaders headers = httpHeaders();
        URI uri = UriComponentsBuilder.fromUriString(apiUrl + "/albums/{albumId}")
                .buildAndExpand(albumId)
                .toUri();
        log.info(uri.toString());
        RequestEntity<Void> requestEntity = RequestEntity
                .get(uri)
                .headers(headers)
                .build();
        return restTemplate.exchange(requestEntity, Album.class).getBody();
    }

    @Override
    @Observed(name = "search")
    public Object searchTracks(String query) {
        log.trace("Inside SpotifyServiceImpl search");
        log.info("search track: "+query);
        return restTemplate.exchange(searchRequest(query, "track"), SpotifyTracksSearch.class).getBody();
    }

    @Override
    public Object searchPlaylists(String query) {
        log.trace("Inside searchPlaylists search");
        log.info("search Playlist: "+query);
        return restTemplate.exchange(searchRequest(query, "playlist"), SpotifyPlaylistSearch.class).getBody();
    }
    @Override
    public Object searchAlbums(String query) {
        log.trace("Inside searchPlaylists search");
        log.info("search Playlist: "+query);
        return restTemplate.exchange(searchRequest(query, "album"), SpotifyAlbumSearch.class).getBody();
    }

    private RequestEntity<Void> searchRequest(String query, String type){
        HttpHeaders headers = httpHeaders();
        URI uri = UriComponentsBuilder.fromUriString(apiUrl + "/search")
                .queryParam("q", query)
                .queryParam("type", type)
                .build()
                .toUri();
        log.info(uri.toString());
        return RequestEntity
                .get(uri)
                .headers(headers)
                .build();
    }

    private RequestEntity<Void> playListRequest(String playlistId){
        HttpHeaders headers = httpHeaders();
        URI uri = UriComponentsBuilder.fromUriString(apiUrl + "/playlists/{playlistId}")
                .buildAndExpand(playlistId)
                .toUri();
        log.info(uri.toString());
        return RequestEntity
                .get(uri)
                .headers(headers)
                .build();
    }

    private RequestEntity<Void> trackRequest(String trackId){
        HttpHeaders headers = httpHeaders();
        URI uri = UriComponentsBuilder.fromUriString(apiUrl + "/tracks/{trackId}")
                .buildAndExpand(trackId)
                .toUri();
        log.info(uri.toString());
        return RequestEntity
                .get(uri)
                .headers(headers)
                .build();
    }

    private HttpHeaders httpHeaders(){
        String accessToken = spotifyAccessTokenRepository.findById(1).orElseThrow().getAccessToken();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

}
