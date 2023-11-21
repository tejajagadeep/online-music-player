package com.spotify.musicservice.dto;

import lombok.Data;
import se.michaelthelin.spotify.enums.ModelObjectType;

import java.util.List;

@Data
public class Album {
    private String albumType;
    private List<Artist> artists;
    private ExternalUrls externalUrls;
    private String href;
    private String id;
    private List<Image> images;
    private String name;
    private String releaseDate;
    private ModelObjectType type;
    private String uri;

    // Getters and setters
}