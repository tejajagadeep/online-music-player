package com.spotify.wishlistservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SpotifyAlbumInfo {
    private Album album;
    private List<Artist> artists;
    private List<String> availableMarkets;
    private int discNumber;
    private long durationMs;
    private boolean isExplicit;
    private ExternalIds externalIds;
    private ExternalUrls externalUrls;
    private String href;
    private String id;
    private boolean isPlayable;
    private LinkedFrom linkedFrom;
    private Restrictions restrictions;
    private String name;
    private int popularity;
    private String previewUrl;
    private int trackNumber;
    private String type;
    private String uri;
    private boolean isLocal;
    // Other fields and methods

    // Getters and setters
}