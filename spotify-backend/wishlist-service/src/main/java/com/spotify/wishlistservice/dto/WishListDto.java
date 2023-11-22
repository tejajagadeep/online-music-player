package com.spotify.wishlistservice.dto;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@Document
@Data
public class WishListDto {

    @Id
    private String username;
    private List<TrackDto> tracks;
}
