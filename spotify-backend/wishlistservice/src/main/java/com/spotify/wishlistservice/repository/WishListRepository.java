package com.spotify.wishlistservice.repository;

import com.spotify.wishlistservice.model.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishListRepository extends JpaRepository<WishList, Long> {
    List<WishList> findAllByUsername(String username);
}
