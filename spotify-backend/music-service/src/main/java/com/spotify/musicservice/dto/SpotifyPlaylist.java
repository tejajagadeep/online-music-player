package com.spotify.musicservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import se.michaelthelin.spotify.enums.ModelObjectType;

import java.util.List;

@Data
public class SpotifyPlaylist {

    @JsonProperty("description")
    private String description;

    @JsonProperty("external-urls")
    private ExternalUrls externalUrls;

    @JsonProperty("followers")
    private Followers followers;

    @JsonProperty("href")
    private String href;

    @JsonProperty("id")
    private String id;

    @JsonProperty("images")
    private List<Image> images;

    @JsonProperty("name")
    private String name;

    @JsonProperty("snapshot_id")
    private String snapshotId;

    @JsonProperty("tracks")
    private Tracks tracks;

    @JsonProperty("type")
    private String type;

    @JsonProperty("uri")
    private String uri;

}
