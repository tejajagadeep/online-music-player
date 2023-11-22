package com.spotify.wishlistservice.model;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@Document
@Data
public class WishList {

    @Id
    private String username;
    private List<SpotifyTrack> tracks;
}
