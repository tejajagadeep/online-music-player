package com.spotify.musicservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
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