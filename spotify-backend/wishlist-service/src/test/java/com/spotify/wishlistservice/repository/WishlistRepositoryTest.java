//package com.spotify.wishlistservice.repository;
//
//import com.spotify.wishlistservice.model.Track;
//import com.spotify.wishlistservice.model.Wishlist;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
//import org.springframework.data.mongodb.repository.MongoRepository;
//import org.springframework.data.mongodb.repository.Query;
//import org.springframework.stereotype.Repository;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@DataMongoTest
//public class WishlistRepositoryTest {
//
//    @Autowired
//    private WishlistRepository wishlistRepository;
//
//
//    @Test
//    public void existsByUsernameAndTrackId_ExistingWishlistAndTrack_ReturnsTrue() {
//        // Arrange
//        Wishlist wishlist = new Wishlist();
//        wishlist.setUsername("testUser");
//        Track track = new Track();
//        track.setId("testTrackId");
//        wishlist.getTracks().add(track);
//        wishlistRepository.save(wishlist);
//
//        // Act
//        boolean exists = wishlistRepository.existsByUsernameAndTrackId("testUser", "testTrackId");
//
//        // Assert
//        assertTrue(exists);
//    }
//
//    @Test
//    public void existsByUsernameAndTrackId_NonExistingWishlist_ReturnsFalse() {
//        // Act
//        boolean exists = wishlistRepository.existsByUsernameAndTrackId("nonExistingUser", "testTrackId");
//
//        // Assert
//        assertFalse(exists);
//    }
//
//    @Test
//    public void existsByUsernameAndTrackId_NonExistingTrack_ReturnsFalse() {
//        // Arrange
//        Wishlist wishlist = new Wishlist();
//        wishlist.setUsername("testUser");
//        wishlistRepository.save(wishlist);
//
//        // Act
//        boolean exists = wishlistRepository.existsByUsernameAndTrackId("testUser", "nonExistingTrackId");
//
//        // Assert
//        assertFalse(exists);
//    }
//}
