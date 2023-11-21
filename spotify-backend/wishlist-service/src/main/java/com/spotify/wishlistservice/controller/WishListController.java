package com.spotify.wishlistservice.controller;

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

    @GetMapping
    public ResponseEntity<Object> getAllWishList(){
            return new ResponseEntity<>(wishListService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<Object> getAllByUsername(@PathVariable String username){
        return new ResponseEntity<>(wishListService.getAllByUsername(username), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> saveWishList(@RequestBody WishList wishList){
            return new ResponseEntity<>(wishListService.saveWishList(wishList), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> removeWishList(@PathVariable long id){
            return new ResponseEntity<>(wishListService.removeWishList(id), HttpStatus.OK);
    }
}
