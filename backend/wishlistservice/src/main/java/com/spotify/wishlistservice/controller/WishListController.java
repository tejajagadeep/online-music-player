package com.spotify.wishlistservice.controller;

import com.spotify.wishlistservice.entity.WishList;
import com.spotify.wishlistservice.exception.ResourceNotFoundException;
import com.spotify.wishlistservice.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1.0/whishlist")
public class WishListController {

    private final WishListService wishListService;

    @Autowired
    public WishListController(WishListService wishListService) {
        this.wishListService = wishListService;
    }

    @GetMapping
    public ResponseEntity<Object> getAllWishList(){
        try {
            return new ResponseEntity<>(wishListService.getAllUsers(), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(new ResourceNotFoundException("No Data to show"), HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping
    public ResponseEntity<Object> saveWishList(@RequestBody WishList wishList){
        try {
            return new ResponseEntity<>(wishListService.saveWishList(wishList), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(new ResourceNotFoundException("Internal server error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> removeWishList(@PathVariable long id){
        try {
            return new ResponseEntity<>(wishListService.removeWishList(id), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(new ResourceNotFoundException("Data with id Doesn't exist"), HttpStatus.NOT_FOUND);
        }
    }
}
