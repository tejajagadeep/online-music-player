package com.spotify.wishlistservice.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WishListDto {

    private String username;
    private List<TrackDto> tracks;
}
