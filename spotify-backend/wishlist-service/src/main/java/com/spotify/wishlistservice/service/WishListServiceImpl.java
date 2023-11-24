package com.spotify.wishlistservice.service;

import com.spotify.wishlistservice.dto.TrackDto;
import com.spotify.wishlistservice.dto.WishListDto;
import com.spotify.wishlistservice.exception.ResourceNotFoundException;
import com.spotify.wishlistservice.model.Track;
import com.spotify.wishlistservice.model.Wishlist;
import com.spotify.wishlistservice.repository.WishListRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class WishListServiceImpl implements WishListService{

    private final WishListRepository wishListRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public WishListServiceImpl(WishListRepository wishListRepository, ModelMapper modelMapper) {
        this.wishListRepository = wishListRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public WishListDto getUserWishList(String username){
        log.info("Service getUserWishList: "+ username);
        return modelMapper.map(wishListRepository.findById(username).orElseThrow(() -> new ResourceNotFoundException("Entity not found with ID: " + username)),WishListDto.class);
    }


    @Override
    public TrackDto saveTrackToWishList(String username, TrackDto trackDto) {
        log.info("Service saveTrackToUsername: "+ username);
        Optional<Wishlist> wishListOptional = wishListRepository.findById(username);
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
        wishListRepository.save(wishList);
        log.info("Track added"+trackDto.toString());
        return trackDto;
    }

    @Override
    public String deleteTrackByUsernameAndTrackId(String username, String trackId) {
        log.info("Service deleteTrackByUsername: "+ username);
        Optional<Wishlist> wishListOptional = Optional.ofNullable(wishListRepository.findById(username).orElseThrow(() -> new ResourceNotFoundException("Username not found")));
        wishListOptional.ifPresent(wishlist -> {
            // Find and remove the track with the specified id
            wishlist.getTracks().removeIf(track -> track.getId().equals(trackId));
            wishListRepository.save(wishlist);
        });
        log.info("Service Track deleted with Id: "+ trackId);
        return "Track with Id: "+trackId+" deleted.";
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
                        existingTrack.setExternalUrls(newTrack.getExternalUrls());
                        existingTrack.setHref(newTrack.getHref());
                        existingTrack.setArtists(newTrack.getArtists());
                        existingTrack.setUri(newTrack.getUri());
                        existingTrack.setType(newTrack.getType());
                        // Update other attributes as needed
                    });
        }
    }
}
