package com.spotify.musicservice.dto;

import lombok.Data;

@Data
public class Track {
    private String addedAt;
    private AddedBy addedBy;
    private boolean isLocal;
    private TrackTrack track;

    // Getters and setters
}

