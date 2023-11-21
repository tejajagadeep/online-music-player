package com.spotify.musicservice.dto;

import lombok.Data;

@Data
public class ExternalIds {
    private String isrc;
    private String ean;
    private String upc;

    // Getters and setters
}
