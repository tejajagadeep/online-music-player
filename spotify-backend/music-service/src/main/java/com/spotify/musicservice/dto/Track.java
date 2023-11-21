package com.spotify.musicservice.dto;

import lombok.Data;
import se.michaelthelin.spotify.enums.ModelObjectType;

import java.util.List;

@Data
public class Track {
    private Album album;
    private List<Artist> artists;
    private ExternalUrls externalUrls;
    private String href;
    private String id;
    private String name;
    private ModelObjectType type;
    private String uri;

    // Getters and setters
}
