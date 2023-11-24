package com.spotify.wishlistservice.service;

import com.spotify.wishlistservice.dto.TrackDto;
import com.spotify.wishlistservice.dto.WishListDto;

public interface WishlistService {
    WishListDto getUserWishlist(String username);

    TrackDto saveTrackToWishlist(String username, TrackDto trackDto);

    String deleteTrackByUsernameAndTrackId(String username, String trackId);
}
