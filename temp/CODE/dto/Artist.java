package com.spotify.musicservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class Artist {
    private ExternalUrls externalUrls;
    private Followers followers;
    private List<String> genres;
    private String href;
    private String id;
    private List<Images> images;
    private String name;
    private int popularity;
    private String type;
    private String uri;

    // Getters and setters
}
