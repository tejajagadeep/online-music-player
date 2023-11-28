package com.spotify.wishlistservice.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Wishlist {

    @Id
    private String username;
    private List<Track> tracks = new ArrayList<>();
//    private List<Track> tracks;
}
