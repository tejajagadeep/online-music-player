package com.spotify.wishlistservice.controller;

import com.spotify.wishlistservice.dto.TrackDto;
import com.spotify.wishlistservice.exception.UnAuthorizedException;
import com.spotify.wishlistservice.service.AuthService;
import com.spotify.wishlistservice.service.WishListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/v1.0/wishlist")
public class WishListController {

    private final WishListService wishListService;

    private final AuthService authService;

    @Autowired
    public WishListController(WishListService wishListService, AuthService authService) {
        this.wishListService = wishListService;
        this.authService = authService;
    }

    @GetMapping("/get-user-wishlist")
    public ResponseEntity<Object> getUserWishList(@RequestHeader("Authorization") String token,@RequestParam String username){
        log.info("Controller getUserWishList: "+username);
        Map<String,String> info= authService.validateToken(token);
        log.info(info+"------inside method getUserWishList-----");
        if(!info.containsKey(username)) {
            log.info(token + "inside method getUserWishList-----__---");
            throw new UnAuthorizedException("Please check User details");
        }
        return new ResponseEntity<>(wishListService.getUserWishList(username), HttpStatus.OK);
    }

    @PostMapping("/save-track-to-wishList")
    public ResponseEntity<Object> saveTrackToWishList(@RequestHeader("Authorization") String token,@RequestParam String username, @RequestBody TrackDto trackDto){
        log.info("Controller saveTrackToWishList: "+username);
        Map<String,String> info= authService.validateToken(token);
        log.info(info+"------inside method saveTrackToWishList-----");
        if(!info.containsKey(username)) {
            log.info(token + "inside method get all-----__---");
            throw new UnAuthorizedException("Please check User Id: "+username);
        }
        return new ResponseEntity<>(wishListService.saveTrackToWishList(username,trackDto),HttpStatus.CREATED);
    }


    @DeleteMapping("/remove-track")
    public  ResponseEntity<Object> deleteTrackByUsernameAndTrackId(@RequestHeader("Authorization") String token,@RequestParam String username, @RequestParam String trackId){
        log.info("Controller deleteTrackByUsernameAndTrackId: "+trackId);
        log.info("Controller getUserWishList: "+username);
        log.info(token+"from front end");
        Map<String,String> info= authService.validateToken(token);
        log.info(info+"------inside method get all-----");
        if(!info.containsKey(username)) {
            log.info(token + "inside method get all-----__---");
            throw new UnAuthorizedException("Please check User details");
        }
        return new ResponseEntity<>(wishListService.deleteTrackByUsernameAndTrackId(username, trackId), HttpStatus.OK);
    }

}
