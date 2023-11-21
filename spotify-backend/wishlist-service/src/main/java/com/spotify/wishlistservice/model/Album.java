package com.spotify.wishlistservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Album {
    private String albumType;
    private int totalTracks;
    private List<String> availableMarkets;
    private ExternalUrls externalUrls;
    private String href;
    private String id;
    private List<Image> images;
    private String name;
    private String releaseDate;
    private String releaseDatePrecision;
    private Restrictions restrictions;
    private String type;
    private String uri;
    private List<Artist> artists;
    // Other fields and methods

    // Getters and setters
}