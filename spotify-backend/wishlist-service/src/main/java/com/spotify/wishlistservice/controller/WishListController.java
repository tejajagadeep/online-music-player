package com.spotify.wishlistservice.controller;

import com.spotify.wishlistservice.dto.TrackDto;
import com.spotify.wishlistservice.service.WishListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1.0/wishlist")
public class WishListController {

    private final WishListService wishListService;

    @Autowired
    public WishListController(WishListService wishListService) {
        this.wishListService = wishListService;
    }

    @GetMapping("/get-user-wishlist")
    public ResponseEntity<Object> getUserWishList(@RequestParam String username){
        log.info("Controller getUserWishList: "+username);
        return new ResponseEntity<>(wishListService.getUserWishList(username), HttpStatus.OK);
    }

    @PostMapping("/save-track-to-wishList")
    public ResponseEntity<Object> saveTrackToWishList(@RequestParam String username, @RequestBody TrackDto trackDto){
        log.info("Controller saveTrackToWishList: "+username);
        return new ResponseEntity<>(wishListService.saveTrackToWishList(username,trackDto),HttpStatus.CREATED);
    }


    @DeleteMapping("/remove-track")
    public  ResponseEntity<Object> deleteTrackByUsernameAndTrackId(@RequestParam String username, @RequestParam String trackId){
        log.info("Controller deleteTrackByUsernameAndTrackId: "+trackId);
        return new ResponseEntity<>(wishListService.deleteTrackByUsernameAndTrackId(username, trackId),HttpStatus.OK);
    }

}
