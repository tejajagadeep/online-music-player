package com.spotify.wishlistservice.service;

import com.spotify.wishlistservice.dto.TrackDto;
import com.spotify.wishlistservice.dto.WishlistDto;

public interface WishlistService {
    WishlistDto getUserWishlist(String username);

    TrackDto saveTrackToWishlist(String username, TrackDto trackDto);

    WishlistDto deleteTrackByUsernameAndTrackId(String username, String trackId);

}
