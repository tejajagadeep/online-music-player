package com.spotify.musicservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class TrackTrack {
    private TrackAlbum album;
    private List<Artist> artists;
    private List<String> availableMarkets;
    private int discNumber;
    private long durationMs;
    private boolean explicit;
    private ExternalIds externalIds;
    private ExternalUrls externalUrls;
    private String href;
    private String id;
    private boolean isPlayable;
    private Restrictions restrictions;
    private String name;
    private int popularity;
    private String previewUrl;
    private int trackNumber;
    private String type;
    private String uri;
    private boolean isLocal;

    // Getters and setters
}

