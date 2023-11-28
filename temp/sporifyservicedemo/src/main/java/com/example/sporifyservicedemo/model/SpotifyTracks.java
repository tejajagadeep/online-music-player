package com.example.sporifyservicedemo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class SpotifyTracks {

    @Id
    private int id;

    @JsonProperty("tracks")
    private TrackTracks tracks;
}
