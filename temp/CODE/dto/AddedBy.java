package com.spotify.musicservice.dto;

import lombok.Data;

@Data
public class AddedBy {
    private ExternalUrls externalUrls;
    private Followers followers;
    private String href;
    private String id;
    private String type;
    private String uri;

    // Getters and setters
}
