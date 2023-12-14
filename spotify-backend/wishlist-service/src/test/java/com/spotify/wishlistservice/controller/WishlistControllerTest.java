//package com.spotify.wishlistservice.controller;
//
//import com.spotify.wishlistservice.dto.TrackDto;
//import com.spotify.wishlistservice.dto.WishlistDto;
//import com.spotify.wishlistservice.service.AuthService;
//import com.spotify.wishlistservice.service.WishlistService;
//import org.apache.coyote.Response;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import static org.mockito.Mockito.*;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@RunWith(MockitoJUnitRunner.class)
//class WishlistControllerTest {
//    @Mock
//    private WishlistService wishlistService;
//
//    @Mock
//    private AuthService authService;
//    @InjectMocks
//    private WishlistController wishlistController;
//
//    @BeforeEach
//    public void init() {
//        MockitoAnnotations.initMocks(this);
//
//    }
//
//    @Test
//    void getUserWishList() {
//        String username="jagadeep";
//        Map<String,String> info=new HashMap<>();
//        info.put(username,"abcdefghijklm");
//        when(authService.validateToken("abcdefghijklm")).thenReturn(info);
//        List<TrackDto> list=new ArrayList<>();
//        TrackDto trackDto=new TrackDto();
//        trackDto.setId("5DgY6Ab0vpyUMKnY9ubFOF");
//        trackDto.setName( "Hukum - Thalaivar Alappara");
//        trackDto.setPreviewUrl("https://p.scdn.co/mp3-preview/25de2a42aced5bd37e33eab44d0aa5aba04305e8?cid=cfdbd6448f364fca80b7aa03228c64c9");
//        trackDto.setType("track");
//
//        list.add(1,trackDto);
//        WishlistDto wishlistDto=new WishlistDto();
//        wishlistDto.setUsername(username);
//        wishlistDto.setTracks(list);
//
//        when(wishlistService.getUserWishlist(username)).thenReturn(wishlistDto);
//
//        ResponseEntity<Object> response=wishlistController.getUserWishlist("abcdefghijk",username);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(wishlistDto, response.getBody());
//
//
//
//    }
//
//    @Test
//    void saveTrackToWishList() {
//        String username="jagadeep";
//        Map<String,String> info=new HashMap<>();
//        info.put(username,"abcdefghijklm");
//        when(authService.validateToken("abcdefghijklm")).thenReturn(info);
//
//        TrackDto trackDto=new TrackDto();
//        trackDto.setId("5DgY6Ab0vpyUMKnY9ubFOF");
//        trackDto.setName( "Hukum - Thalaivar Alappara");
//        trackDto.setPreviewUrl("https://p.scdn.co/mp3-preview/25de2a42aced5bd37e33eab44d0aa5aba04305e8?cid=cfdbd6448f364fca80b7aa03228c64c9");
//        trackDto.setType("track");
//
//        ResponseEntity<Object> response=wishlistController.saveTrackToWishlist("abcdefghijk",username,trackDto);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(trackDto, response.getBody());
//
//    }
//
//    @Test
//    void deleteTrackByUsernameAndTrackId() {
//        String username="jagadeep";
//        String trackId="abcdef";
//        Map<String,String> info=new HashMap<>();
//        info.put(username,"abcdefghijklm");
//        when(authService.validateToken("abcdefghijklm")).thenReturn(info);
//        WishlistDto wishlistDto = new WishlistDto();
//        wishlistDto.setUsername(username);
//
//        when(wishlistService.deleteTrackByUsernameAndTrackId(username,trackId)).thenReturn(wishlistDto);
//
//        ResponseEntity<Object> response=wishlistController.deleteTrackByUsernameAndTrackId("abcdefghijklm",username,trackId);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals("Track with Id: "+trackId+" deleted.", response.getBody());
//
//    }
//}