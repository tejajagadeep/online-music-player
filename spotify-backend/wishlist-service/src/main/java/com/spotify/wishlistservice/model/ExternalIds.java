package com.spotify.wishlistservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExternalIds {
    private String isrc;
    private String ean;
    private String upc;
    // Getters and setters
}
