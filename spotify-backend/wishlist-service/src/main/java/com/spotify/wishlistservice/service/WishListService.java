package com.spotify.wishlistservice.service;

import com.spotify.wishlistservice.dto.WishListDto;
import com.spotify.wishlistservice.model.WishList;

import java.util.List;

public interface WishListService {
    List<WishListDto> getAllUsers();

    List<WishListDto> getAllByUsername(String username);

    WishListDto saveWishList(WishList wishList);

    WishListDto removeWishList(long id);

}
