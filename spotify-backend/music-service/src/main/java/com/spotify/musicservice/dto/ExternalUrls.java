package com.spotify.musicservice.dto;

public class ExternalUrls {
    private String externalUrls;

    public String getExternalUrls() {
        return externalUrls.replace("{spotify=","").replace("}","");

    }

    public void setExternalUrls(String externalUrls) {
        this.externalUrls = externalUrls;
    }
}