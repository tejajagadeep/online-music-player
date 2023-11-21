package com.spotify.wishlistservice.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@Document
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WishList {

    @Id
    private String id;

    private String username;
    private String songName;
    //private List<SpotifyAlbumInfo> spotifyAlbumInfos;
}
