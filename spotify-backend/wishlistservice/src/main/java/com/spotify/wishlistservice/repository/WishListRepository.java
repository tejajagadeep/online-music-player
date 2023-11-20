package com.spotify.wishlistservice.repository;

import com.spotify.wishlistservice.model.WishList;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishListRepository extends MongoRepository<WishList, Long> {
    List<WishList> findAllByUsername(String username);
}
