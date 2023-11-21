package com.spotify.musicservice.dto;

import lombok.Data;
import se.michaelthelin.spotify.enums.ModelObjectType;

import java.util.List;

@Data
public class SpotifyPlaylist {
    private String description;
    private ExternalUrls externalUrls;
    private Followers followers;
    private String href;
    private String id;
    private List<Image> images;
    private String name;
    private String snapshotId;
    private Tracks tracks;
    private ModelObjectType type;
    private String uri;
}
