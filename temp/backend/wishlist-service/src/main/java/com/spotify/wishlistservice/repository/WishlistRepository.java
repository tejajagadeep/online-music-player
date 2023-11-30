package com.spotify.wishlistservice.repository;

import com.spotify.wishlistservice.model.Wishlist;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishlistRepository extends MongoRepository<Wishlist, String> {
    void deleteTrackByUsernameAndTracksId(String username, String trackId);
}
