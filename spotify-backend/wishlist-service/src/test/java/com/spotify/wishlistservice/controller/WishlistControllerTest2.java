//package com.spotify.wishlistservice.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.spotify.wishlistservice.dto.WishlistDto;
//import com.spotify.wishlistservice.service.AuthService;
//import com.spotify.wishlistservice.service.WishlistService;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import java.util.Collections;
//import java.util.Map;
//
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//class WishlistControllerTest2 {
//
//    @Mock
//    private AuthService authService;
//
//    @Mock
//    private WishlistService wishlistService;
//
//    @InjectMocks
//    private WishlistController yourController;
//
//    private final ObjectMapper objectMapper = new ObjectMapper();
//
//    private final MockMvc mockMvc;
//
//    public WishlistControllerTest2() {
//        MockitoAnnotations.initMocks(this);
//        this.mockMvc = MockMvcBuilders.standaloneSetup(yourController).build();
//    }
//
//    @Test
//    void testGetUserWishlist() throws Exception {
//        // Mock data
//        String token = "your_mocked_token";
//        String username = "testUser";
//        Map<String, String> info = Collections.singletonMap(username, "some_value");
//
//        // Mock authService behavior
//        when(authService.validateToken(token)).thenReturn(info);
//
//        // Mock wishlistService behavior
//        when(wishlistService.getUserWishlist(username)).thenReturn((WishlistDto) Collections.emptyList());
//
//        // Perform the test
//        mockMvc.perform(get("/userWishList")
//                        .header(HttpHeaders.AUTHORIZATION, token)
//                        .param("username", username))
//                .andExpect(status().isOk())
//                .andExpect(content().json("[]"));
//
//        // Verify interactions
//        verify(authService, times(1)).validateToken(token);
//        verify(wishlistService, times(1)).getUserWishlist(username);
//    }
//
//    @Test
//    void testGetUserWishlistUnauthorized() throws Exception {
//        // Mock data
//        String token = "your_mocked_token";
//        String username = "unauthorizedUser";
//        Map<String, String> info = Collections.emptyMap(); // Empty info means unauthorized
//
//        // Mock authService behavior
//        when(authService.validateToken(token)).thenReturn(info);
//
//        // Perform the test
//        mockMvc.perform(get("/userWishList")
//                        .header(HttpHeaders.AUTHORIZATION, token)
//                        .param("username", username))
//                .andExpect(status().isUnauthorized());
//
//        // Verify interactions
//        verify(authService, times(1)).validateToken(token);
//    }
//
//
//
//    @Test
//    void favoriteExists() {
//    }
//
//    @Test
//    void saveTrackToWishlist() {
//    }
//
//    @Test
//    void deleteTrackByUsernameAndTrackId() {
//    }
//}