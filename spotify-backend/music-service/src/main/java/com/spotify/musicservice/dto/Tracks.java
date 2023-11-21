package com.spotify.musicservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class Tracks {
    private String href;
    private List<Item> items;
    private int limit;
    private Object next;
    private int offset;
    private Object previous;
    private int total;

    // Getters and setters
}