package com.spotify.wishlistservice.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.spotify.wishlistservice.dto.TrackDto;
import com.spotify.wishlistservice.dto.WishlistDto;
import com.spotify.wishlistservice.exception.ResourceNotFoundException;
import com.spotify.wishlistservice.model.Track;
import com.spotify.wishlistservice.model.Wishlist;
import com.spotify.wishlistservice.repository.WishlistRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

class WishlistServiceImplTest2 {

    private WishlistRepository wishlistRepository;
    private ModelMapper modelMapper;
    private WishlistServiceImpl wishlistService;

    @BeforeEach
    void setUp() {
        wishlistRepository = mock(WishlistRepository.class);
        modelMapper = new ModelMapper();
        wishlistService = new WishlistServiceImpl(wishlistRepository, modelMapper);
    }

    @Test
    void testGetUserWishlist() {
        // Arrange
        String username = "testUser";
        Wishlist wishlist = new Wishlist();
        wishlist.setUsername(username);

        when(wishlistRepository.findById(username)).thenReturn(Optional.of(wishlist));

        // Act
        WishlistDto result = wishlistService.getUserWishlist(username);

        // Assert
        assertNotNull(result);
        assertEquals(username, result.getUsername());
    }

    @Test
    void testGetUserWishlistNotFound() {
        // Arrange
        String username = "nonexistentUser";

        when(wishlistRepository.findById(username)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> wishlistService.getUserWishlist(username));
    }

    @Test
    void testSaveTrackToWishlist() {
        // Arrange
        String username = "testUser";
        TrackDto trackDto = new TrackDto();
        trackDto.setId("track123");
        Wishlist wishlist = new Wishlist();
        wishlist.setUsername(username);

        when(wishlistRepository.findById(username)).thenReturn(Optional.of(wishlist));

        // Act
        TrackDto result = wishlistService.saveTrackToWishlist(username, trackDto);

        // Assert
        assertNotNull(result);
        assertEquals("track123", result.getId());
        assertTrue(wishlist.getTracks().stream().anyMatch(track -> "track123".equals(track.getId())));
    }

    @Test
    void testSaveTrackToWishlistNewUser() {
        // Arrange
        String username = "newUser";
        TrackDto trackDto = new TrackDto();
        trackDto.setId("track456");

        when(wishlistRepository.findById(username)).thenReturn(Optional.empty());

        // Act
        TrackDto result = wishlistService.saveTrackToWishlist(username, trackDto);

        // Assert
        assertNotNull(result);
        assertEquals("track456", result.getId());
        verify(wishlistRepository, times(1)).save(any(Wishlist.class));
    }

    @Test
    void testDeleteTrackByUsernameAndTrackId() {
        // Arrange
        String username = "testUser";
        String trackId = "track123";
        Wishlist wishlist = new Wishlist();
        wishlist.setUsername(username);
        Track track = new Track();
        track.setId(trackId);
        wishlist.setTracks(List.of(track));

        when(wishlistRepository.findById(username)).thenReturn(Optional.of(wishlist));

        // Act
        String result = wishlistService.deleteTrackByUsernameAndTrackId(username, trackId);

        // Assert
        assertEquals("Track with Id: track123 deleted.", result);

        // Verify that the repository's save method was called with the updated wishlist
        ArgumentCaptor<Wishlist> wishlistCaptor = ArgumentCaptor.forClass(Wishlist.class);
        verify(wishlistRepository).save(wishlistCaptor.capture());

        // Verify that the track was removed from the wishlist
        Wishlist updatedWishlist = wishlistCaptor.getValue();
        assertTrue(updatedWishlist.getTracks().isEmpty());
    }

    @Test
    void testDeleteTrackByUsernameAndTrackIdUserNotFound() {
        // Arrange
        String username = "nonexistentUser";
        String trackId = "track123";

        when(wishlistRepository.findById(username)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> wishlistService.deleteTrackByUsernameAndTrackId(username, trackId));

        // Verify that the repository's save method was not called
        verify(wishlistRepository, never()).save(any(Wishlist.class));
    }
}