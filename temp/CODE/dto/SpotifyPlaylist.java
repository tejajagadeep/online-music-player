package com.spotify.musicservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class SpotifyPlaylist {
    private boolean collaborative;
    private String description;
    private ExternalUrls externalUrls;
    private Followers followers;
    private String href;
    private String id;
    private List<Images> images;
    private String name;
    private Owner owner;
    private boolean isPublic;
    private String snapshotId;
    private Tracks tracks;
    private String type;
    private String uri;

    // Getters and setters
}
