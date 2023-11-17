package com.spotify.wishlistservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LinkedFrom {
    private ExternalUrls externalUrls;
    private String href;
    private String id;
    private String type;
    private String uri;
    // Getters and setters
}