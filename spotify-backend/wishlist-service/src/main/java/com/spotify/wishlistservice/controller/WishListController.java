package com.spotify.wishlistservice.controller;

import com.spotify.wishlistservice.exception.ResourceNotFoundException;
import com.spotify.wishlistservice.model.WishList;
import com.spotify.wishlistservice.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1.0/wishlist")
public class WishListController {

    private final WishListService wishListService;

    @Autowired
    public WishListController(WishListService wishListService) {
        this.wishListService = wishListService;
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<Object> getAllByUsername(@PathVariable String username){
        try {
            return new ResponseEntity<>(wishListService.getAllByUsername(username), HttpStatus.OK);

        } catch (Exception e){
            throw new ResourceNotFoundException("Resource not found");
        }
    }

    @GetMapping("/get-track/{trackId}")
    public ResponseEntity<Object> getTrack(@PathVariable String trackId ) {
        try {
        return new ResponseEntity<>(wishListService.getTrack(trackId), HttpStatus.OK);

    } catch (Exception e){
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    }


    @PostMapping()
    public ResponseEntity<Object> saveWishList(@RequestBody WishList wishList){
        try {
            return new ResponseEntity<>(wishListService.saveWishList(wishList), HttpStatus.OK);

    } catch (Exception e){
        throw new ResourceNotFoundException("Resource not found");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> removeWishList(@PathVariable long id){
        try {
            return new ResponseEntity<>(wishListService.removeWishList(id), HttpStatus.OK);

        } catch (Exception e){
        throw new ResourceNotFoundException("Resource not found");
        }
    }
}
