package com.spotify.wishlistservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WishListDto {

    private long id;
    private String username;
    private String songName;
    private String songUri;
}