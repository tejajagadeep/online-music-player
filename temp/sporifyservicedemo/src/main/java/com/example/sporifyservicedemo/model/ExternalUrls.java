package com.example.sporifyservicedemo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.net.URI;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class ExternalUrls {
    @JsonProperty("spotify")
    private String spotify;
}