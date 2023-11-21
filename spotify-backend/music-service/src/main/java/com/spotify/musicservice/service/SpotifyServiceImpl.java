package com.spotify.musicservice.service;

import com.spotify.musicservice.dto.SpotifyPlaylist;
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
import java.util.Optional;

@Service
@Slf4j
public class SpotifyServiceImpl implements SpotifyService{

    private final SpotifyAccessTokenRepository spotifyAccessTokenRepository;

    private final RestTemplate restTemplate;
    @Value("${spotify.account.url}")
    private String accountApiUrl;

    @Value("${spotify.api.url}")
    private String apiUrl;
    @Value("${spotify.client.id}")
    private String clientId;

    @Value("${spotify.redirect.uri}")
    private String redirectUri;

    @Value("${spotify.client.secret}")
    private String clientSecret;

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
//        return spotifyAccessTokenRepository.save(Optional.ofNullable(responseEntity.getBody()).orElseThrow());

        return spotifyAccessTokenRepository.save(responseEntity.getBody());
    }

    @Override
    public SpotifyPlaylist getBillBoard100Playlist() {
        String playlistId="6UeSakyzhiEt4NB3UAd6NQ";
        String accessToken = spotifyAccessTokenRepository.findById(1L).orElseThrow().getAccessToken();
        log.info(accessToken);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.setContentType(MediaType.APPLICATION_JSON);
        URI uri = UriComponentsBuilder.fromUriString(apiUrl + "/playlists/{playlistId}")
                .buildAndExpand(playlistId)
                .toUri();
        RequestEntity<Void> requestEntity = RequestEntity
                .get(uri)
                .headers(headers)
                .build();
        log.info(requestEntity.toString());
        return restTemplate.exchange(requestEntity, SpotifyPlaylist.class).getBody();
    }
    @Override
    public SpotifyPlaylist getTodayTopHits() {
        String playlistId="37i9dQZF1DXcBWIGoYBM5M";
        String accessToken = spotifyAccessTokenRepository.findById(1L).orElseThrow().getAccessToken();
        log.info(accessToken);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.setContentType(MediaType.APPLICATION_JSON);
        URI uri = UriComponentsBuilder.fromUriString(apiUrl + "/playlists/{playlistId}")
                .buildAndExpand(playlistId)
                .toUri();
        RequestEntity<Void> requestEntity = RequestEntity
                .get(uri)
                .headers(headers)
                .build();
        log.info(requestEntity.toString());
        return restTemplate.exchange(requestEntity, SpotifyPlaylist.class).getBody();
    }


}
