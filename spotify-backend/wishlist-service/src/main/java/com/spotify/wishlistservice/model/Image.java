package com.spotify.wishlistservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Image {
    private String url;
    private int height;
    private int width;
    // Getters and setters
}