package com.spotify.musicservice.dto;

import lombok.Data;

@Data
public class Artist {
    private ExternalUrls externalUrls;
    private String href;
    private String id;
    private String name;
    private String type;
    private String uri;

    // Getters and setters
}