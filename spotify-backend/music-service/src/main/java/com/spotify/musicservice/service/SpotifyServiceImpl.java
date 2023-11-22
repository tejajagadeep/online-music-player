package com.spotify.musicservice.service;

import com.spotify.musicservice.dto.SpotifyPlaylist;
import com.spotify.musicservice.dto.SpotifyTrack;
import com.spotify.musicservice.dto.Track;
import com.spotify.musicservice.exception.ResourceNotFoundException;
import com.spotify.musicservice.model.SpotifyAccessToken;
import com.spotify.musicservice.repository.SpotifyAccessTokenRepository;
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
    public SpotifyAccessToken getSpotifyAccessToken() {

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

        if (spotifyAccessToken != null) {
            spotifyAccessToken.setId(1L);
            return spotifyAccessTokenRepository.save(spotifyAccessToken);
        } else {
            throw new ResourceNotFoundException("Resource Not Found");
        }
    }

    @Override
    public SpotifyPlaylist getBillBoard100Playlist() {
        String playlistId="6UeSakyzhiEt4NB3UAd6NQ";
        RequestEntity<Void> requestEntity = playListRequest(playlistId);

        return restTemplate.exchange(requestEntity, SpotifyPlaylist.class).getBody();
    }
    @Override
    public SpotifyPlaylist getTodayTopHits() {
        String playlistId="37i9dQZF1DXcBWIGoYBM5M";
        RequestEntity<Void> requestEntity = playListRequest(playlistId);
        return restTemplate.exchange(requestEntity, SpotifyPlaylist.class).getBody();
    }

    @Override
    public Track getTrack(String trackId) {
        log.info("get Track: "+trackId);
        RequestEntity<Void> requestEntity = trackRequest(trackId);
        return restTemplate.exchange(requestEntity, Track.class).getBody();
    }

    @Override
    public Object search(String query) {
        return restTemplate.exchange(searchRequest(query), SpotifyTrack.class).getBody();
    }

    private RequestEntity<Void> searchRequest(String query){
        HttpHeaders headers = httpHeaders();
        URI uri = UriComponentsBuilder.fromUriString(apiUrl + "/search")
                .queryParam("q", query)
                .queryParam("type", "track")
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
        return RequestEntity
                .get(uri)
                .headers(headers)
                .build();
    }

    private HttpHeaders httpHeaders(){
        String accessToken = spotifyAccessTokenRepository.findById(1L).orElseThrow().getAccessToken();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

}
