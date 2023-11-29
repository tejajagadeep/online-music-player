package com.spotify.wishlistservice.service;

import com.spotify.wishlistservice.dto.TrackDto;
import com.spotify.wishlistservice.dto.WishlistDto;
import com.spotify.wishlistservice.exception.ResourceNotFoundException;
import com.spotify.wishlistservice.model.Track;
import com.spotify.wishlistservice.model.Wishlist;
import com.spotify.wishlistservice.repository.WishlistRepository;
import io.micrometer.observation.annotation.Observed;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Observed(name = "wishlist.service.impl")
public class WishlistServiceImpl implements WishlistService {

    private final WishlistRepository wishlistRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public WishlistServiceImpl(WishlistRepository wishlistRepository, ModelMapper modelMapper) {
        this.wishlistRepository = wishlistRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Observed(name = "get.user.wishlist")
    public WishlistDto getUserWishlist(String username){
        log.info("Service getUserWishList: "+ username);
        return modelMapper.map(wishlistRepository.findById(username).orElseThrow(() -> new ResourceNotFoundException("Entity not found with ID: " + username)), WishlistDto.class);
    }


    @Override
    @Observed(name = "save.track.to.wishlist")
    public TrackDto saveTrackToWishlist(String username, TrackDto trackDto) {
        log.info("Service saveTrackToUsername: "+ username);
        Optional<Wishlist> wishListOptional = wishlistRepository.findById(username);
        Track track = modelMapper.map(trackDto, Track.class);
        Wishlist wishList;
        if (wishListOptional.isPresent()) {
            // User's wish list exists, add or update the track
            wishList = wishListOptional.get();
            addOrUpdateTrack(wishList, track);
        } else {
            // User's wish list doesn't exist, create a new wish list
            wishList = new Wishlist();
            wishList.setUsername(username);
            wishList.setTracks(List.of(track));
        }

        // Save the updated wish list
        wishlistRepository.save(wishList);
        log.info("Track added"+trackDto.toString());
        return trackDto;
    }

    @Override
    @Observed(name = "delete.track.by.username.and.track.id")
    public String deleteTrackByUsernameAndTrackId(String username, String trackId) {
        log.info("Service deleteTrackByUsername: " + username);

        Wishlist wishlist = wishlistRepository.findById(username)
                .orElseThrow(() -> new ResourceNotFoundException("Username not found"));

        // Create a new mutable list from the existing tracks
        List<Track> mutableTracks = new ArrayList<>(wishlist.getTracks());

        // Find and remove the track with the specified id
        mutableTracks.removeIf(track -> track.getId().equals(trackId));

        // Update the wishlist with the new list of tracks
        wishlist.setTracks(mutableTracks);

        // Save the updated wishlist
        wishlistRepository.save(wishlist);

        log.info("Service Track deleted with Id: " + trackId);
        return "Track with Id: " + trackId + " deleted.";
    }



    private void addOrUpdateTrack(Wishlist wishList, Track newTrack) {
        // Check if the track with the same ID already exists in the wish list
        boolean trackExists = wishList.getTracks().stream()
                .anyMatch(track -> track.getId().equals(newTrack.getId()));

        if (!trackExists) {
            // Track doesn't exist, add it to the wish list
            wishList.getTracks().add(newTrack);
        } else {
            // Track already exists, update it (if needed)
            wishList.getTracks().stream()
                    .filter(track -> track.getId().equals(newTrack.getId()))
                    .findFirst()
                    .ifPresent(existingTrack -> {
                        // Update track attributes if needed
                        existingTrack.setName(newTrack.getName());
                        existingTrack.setPreviewUrl(newTrack.getPreviewUrl());
                        existingTrack.setAlbum(newTrack.getAlbum());
                        existingTrack.setExternal_urls(newTrack.getExternal_urls());
                        existingTrack.setArtists(newTrack.getArtists());
                        existingTrack.setType(newTrack.getType());
                        // Update other attributes as needed
                    });
        }
    }
}
