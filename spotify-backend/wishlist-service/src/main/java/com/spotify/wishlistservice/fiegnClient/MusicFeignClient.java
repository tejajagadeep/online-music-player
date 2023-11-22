package com.spotify.wishlistservice.fiegnClient;

import com.spotify.wishlistservice.model.Track;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("music-service")
public interface MusicFeignClient {

    @GetMapping("/api/v1/music/get-track/{trackId}")
    public Track getTrack(@PathVariable String trackId );
}
