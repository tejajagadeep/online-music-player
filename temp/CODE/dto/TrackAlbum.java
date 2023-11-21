package com.spotify.musicservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class TrackAlbum {
    private String albumType;
    private int totalTracks;
    private List<String> availableMarkets;
    private ExternalUrls externalUrls;
    private String href;
    private String id;
    private List<Images> images;
    private String name;
    private String releaseDate;
    private String releaseDatePrecision;
    private Restrictions restrictions;
    private String type;
    private String uri;
    private List<Artist> artists;

    // Getters and setters
}
