package com.spotify.musicservice.service;

import com.spotify.musicservice.configuration.SpotifyConfiguration;
import com.spotify.musicservice.dto.SpotifyPlaylist;
import com.spotify.musicservice.model.UserDetails;
import com.spotify.musicservice.repository.UserDetailsRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import se.michaelthelin.spotify.model_objects.specification.*;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeRequest;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeUriRequest;
import se.michaelthelin.spotify.requests.data.library.GetCurrentUsersSavedAlbumsRequest;
import se.michaelthelin.spotify.requests.data.personalization.simplified.GetUsersTopTracksRequest;
import se.michaelthelin.spotify.requests.data.playlists.GetPlaylistRequest;
import se.michaelthelin.spotify.requests.data.playlists.GetPlaylistsItemsRequest;
import se.michaelthelin.spotify.requests.data.users_profile.GetCurrentUsersProfileRequest;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class SpotifyServiceImpl implements SpotifyService{

    private final SpotifyConfiguration spotifyConfiguration;
    private final UserDetailsRepository userDetailsRepository;
    private final ModelMapper modelMapper;


    @Value("${spotify.client.id}")
    private String clientId;

    @Value("${spotify.redirect.uri}")
    private String redirectUri;

    @Value("${spotify.client.secret}")
    private String clientSecret;

    @Autowired
    public SpotifyServiceImpl(SpotifyConfiguration spotifyConfiguration, UserDetailsRepository userDetailsRepository, ModelMapper modelMapper) {
        this.spotifyConfiguration = spotifyConfiguration;
        this.userDetailsRepository = userDetailsRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public String authorize() {
        SpotifyApi object = spotifyConfiguration.getSpotifyObject();

        AuthorizationCodeUriRequest authorizationCodeUriRequest = object.authorizationCodeUri()
                .scope("user-read-private user-read-email user-library-read user-top-read")
                .show_dialog(true)
                .build();

        final URI uri = authorizationCodeUriRequest.execute();
        return uri.toString();
    }

    @Override
    public String callback(String code) {
        log.info("code: " + code);
        SpotifyApi object = spotifyConfiguration.getSpotifyObject();

        // Check if the code is present in the request
        if (code != null && !code.isEmpty()) {
            // If code is present, perform token exchange
            handleTokenExchange(code, object);
            return "Token exchange successful";
        } else {
            // If code is not present, initiate authorization flow
            AuthorizationCodeUriRequest authorizationCodeUriRequest = object.authorizationCodeUri()
                    .scope("user-library-read")
                    .show_dialog(true)
                    .build();

            final URI uri = authorizationCodeUriRequest.execute();
            return "redirect:" + uri.toString();
        }
    }

    @Override
    public List<Track> getUserTopSongs(String userId) {
        UserDetails userDetails = userDetailsRepository.findByRefId(userId);

        SpotifyApi object = spotifyConfiguration.getSpotifyObject();
        object.setAccessToken(userDetails.getAccessToken());
        object.setRefreshToken(userDetails.getRefreshToken());

        final GetUsersTopTracksRequest getUsersTopTracksRequest = object.getUsersTopTracks()
                .time_range("medium_term")
                .limit(10)
                .offset(0)
                .build();

        try {
            final Paging<Track> trackPaging = getUsersTopTracksRequest.execute();
            return Arrays.asList(trackPaging.getItems());
        } catch (Exception e) {
            log.info("Exception occurred while fetching top songs: " + e);

        }
        return null;
    }

    public SavedAlbum[] getCurrentUserSavedAlbum(String userId) {
        UserDetails userDetails = userDetailsRepository.findByRefId(userId);

        SpotifyApi object = spotifyConfiguration.getSpotifyObject();
        object.setAccessToken(userDetails.getAccessToken());
        object.setRefreshToken(userDetails.getRefreshToken());

        final GetCurrentUsersSavedAlbumsRequest getUsersTopArtistsRequest = object.getCurrentUsersSavedAlbums()
                .limit(50)
                .offset(0)
                .build();

        try {
            final Paging<SavedAlbum> artistPaging = getUsersTopArtistsRequest.execute();

            return artistPaging.getItems();
        } catch (Exception e) {
            log.info("Exception occured while fetching user saved album: " + e);
        }

        return new SavedAlbum[0];
    }

    @Override
    public List<PlaylistTrack> getBillBoard100() {
        SpotifyApi object = spotifyConfiguration.getSpotifyObject();
        String billBoard100="6UeSakyzhiEt4NB3UAd6NQ";
        GetPlaylistsItemsRequest getPlaylistsItemsRequest = object.getPlaylistsItems(billBoard100)
                .build();
        try {
            Paging<PlaylistTrack> playlistTrackPaging = getPlaylistsItemsRequest.execute();
            return (Arrays.asList(playlistTrackPaging.getItems()));
        } catch (Exception e) {
            log.info("Exception occurred while handling token exchange: " + e);
        }
        return null;
    }

    @Override
    public Playlist getBillBoard100Playlist() {
        SpotifyApi object = spotifyConfiguration.getSpotifyObject();
        String billBoard100="6UeSakyzhiEt4NB3UAd6NQ";
        GetPlaylistRequest getPlaylistRequest = object.getPlaylist(billBoard100)
                .build();

        try {
            Playlist playlistTrackPaging = getPlaylistRequest.execute();
            return getPlaylistRequest.execute();
        } catch (Exception e) {
            log.info("Exception occurred while handling token exchange: " + e);
        }
        return null;
    }
    @Override
    public SpotifyPlaylist getBillBoard100PlaylistObj() {
        SpotifyApi object = spotifyConfiguration.getSpotifyObject();
        String billBoard100="6UeSakyzhiEt4NB3UAd6NQ";
        GetPlaylistRequest getPlaylistRequest = object.getPlaylist(billBoard100)
                .build();

        try {
            Playlist playlistTrackPaging = getPlaylistRequest.execute();
//            ObjectMapper objectMapper = new ObjectMapper();
//            return objectMapper.readValue(getPlaylistRequest.execute().toString(), SpotifyPlaylist.class);

            return modelMapper.map(getPlaylistRequest.execute(), SpotifyPlaylist.class);
        } catch (Exception e) {
            log.info("Exception occurred while handling token exchange: " + e);
        }
        return null;
    }
    private void handleTokenExchange(String code, SpotifyApi object) {
        AuthorizationCodeRequest authorizationCodeRequest = object.authorizationCode(code).build();
        User user = null;

        try {
            final AuthorizationCodeCredentials authorizationCode = authorizationCodeRequest.execute();

            object.setAccessToken(authorizationCode.getAccessToken());
            object.setRefreshToken(authorizationCode.getRefreshToken());

            final GetCurrentUsersProfileRequest getCurrentUsersProfile = object.getCurrentUsersProfile().build();
            user = getCurrentUsersProfile.execute();
            log.info("user details");
            log.info(user.toString());
            log.info(authorizationCode.getAccessToken());
            log.info(authorizationCode.getRefreshToken());
            insertOrUpdateUserDetails(user, authorizationCode.getAccessToken(), authorizationCode.getRefreshToken());
        } catch (Exception e) {
            log.info("Exception occurred while handling token exchange: " + e);
        }
    }

    private UserDetails insertOrUpdateUserDetails(User user, String accessToken, String refreshToken) {
        UserDetails userDetails = userDetailsRepository.findByRefId(user.getId());
        if(Objects.isNull(userDetails)){
            userDetails = new UserDetails();
        }
        userDetails.setAccessToken(accessToken);
        userDetails.setRefreshToken(refreshToken);
        userDetails.setTokenType(String.valueOf(user.getType()));
        userDetails.setRefId(user.getId());

        log.info(user.toString());
        return userDetailsRepository.save(userDetails);
    }
}
