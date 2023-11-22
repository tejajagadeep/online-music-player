package com.spotify.wishlistservice.service;

import com.spotify.wishlistservice.dto.TrackDto;
import com.spotify.wishlistservice.dto.WishListDto;
import com.spotify.wishlistservice.model.Track;
import com.spotify.wishlistservice.model.WishList;

import java.util.List;

public interface WishListService {
    WishListDto getUserWishList(String username);

    TrackDto saveTrackToWishList(String username, TrackDto trackDto);

    String deleteTrackByUsernameAndTrackId(String username, String trackId);
}
