package com.spotify.wishlistservice.service;

import com.spotify.wishlistservice.dto.TrackDto;
import com.spotify.wishlistservice.dto.WishListDto;

public interface WishListService {
    WishListDto getUserWishList(String username);

    TrackDto saveTrackToWishList(String username, TrackDto trackDto);

    String deleteTrackByUsernameAndTrackId(String username, String trackId);
}
