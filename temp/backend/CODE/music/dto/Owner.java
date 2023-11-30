package com.spotify.musicservice.dto;

import lombok.Data;

@Data
public class Owner {
    private ExternalUrls externalUrls;
    private Followers followers;
    private String href;
    private String id;
    private String type;
    private String uri;
    private String displayName;

    // Getters and setters
}
