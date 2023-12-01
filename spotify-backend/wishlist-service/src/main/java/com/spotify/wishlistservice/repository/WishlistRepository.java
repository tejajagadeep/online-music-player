package com.spotify.wishlistservice.repository;

import com.spotify.wishlistservice.model.Wishlist;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WishlistRepository extends MongoRepository<Wishlist, String> {
    void deleteTrackByUsernameAndTracksId(String username, String trackId);

//    @Query(value = "{ 'username': ?0, 'tracks.id': ?1 }", exists = true)
    @Query(value = "{ 'username': ?0, 'tracks': { $elemMatch: { 'id': ?1 } } }", exists = true)
    boolean existsByUsernameAndTrackId(String username, String id);
}
