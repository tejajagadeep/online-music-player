package com.spotify.musicservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class Tracks {
    private String href;
    private int limit;
    private String next;
    private int offset;
    private String previous;
    private int total;
    private List<Track> items;

    // Getters and setters
}
