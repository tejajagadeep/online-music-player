package com.spotify.wishlistservice.service;

import com.spotify.wishlistservice.dto.TrackDto;
import com.spotify.wishlistservice.dto.WishlistDto;

public interface WishlistService {
    WishlistDto getUserWishlist(String username);

    Boolean favoriteExists(String username, String trackId);

    TrackDto saveTrackToWishlist(String username, TrackDto trackDto);

    String deleteTrackByUsernameAndTrackId(String username, String trackId);

}
