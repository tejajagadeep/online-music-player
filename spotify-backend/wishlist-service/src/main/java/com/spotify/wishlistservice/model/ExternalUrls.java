package com.spotify.wishlistservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class ExternalUrls {
    @JsonProperty("spotify")
    private String spotify;

//    public String getExternalUrls() {
//        return externalUrls.replace("{spotify=","").replace("}","");
//
//    }
//
//    public void setExternalUrls(String externalUrls) {
//        this.externalUrls = externalUrls;
//    }
}