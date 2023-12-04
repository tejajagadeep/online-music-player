package com.spotify.wishlistservice.service;

import com.spotify.wishlistservice.dto.AlbumDto;
import com.spotify.wishlistservice.dto.TrackDto;
import com.spotify.wishlistservice.dto.WishlistDto;
import com.spotify.wishlistservice.exception.ResourceNotFoundException;
import com.spotify.wishlistservice.model.*;
import com.spotify.wishlistservice.repository.WishlistRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class WishlistServiceImplTest {

    @InjectMocks
    WishlistServiceImpl wishlistService;

    @Mock
    WishlistRepository wishlistRepository;

    @Mock
    ModelMapper modelMapper;

    Wishlist wishlist = new Wishlist();
    Track mockTrack = new Track();

    WishlistDto wishlistDto= new WishlistDto();
    TrackDto mockTrackDto = new TrackDto();

    @Mock
    Track track;

    @BeforeEach
    void addMocks(){
        MockitoAnnotations.openMocks(this);



        ExternalUrls externalUrls = new ExternalUrls();
        externalUrls.setSpotify("https://open.spotify.com/track/2wAJTrFhCnQyNSD3oUgTZO");

        mockTrack.setName("Work Out");
        mockTrack.setId("2wAJTrFhCnQyNSD3oUgTZO");
        mockTrack.setType("track");
        mockTrack.setExternalUrls(externalUrls);



        Album mockAlbum = new Album();
        mockAlbum.setName("Cole World: The Sideline Story");
        mockAlbum.setId("0fhmJYVhW0e4i33pCLPA5i");
        mockAlbum.setType("album");
        mockAlbum.setReleaseDate("2011-09-27");
        mockAlbum.setExternalUrls(externalUrls);
        mockTrack.setAlbum(mockAlbum);

        Artist mockArtist = new Artist();
        mockArtist.setName("J. Cole");
        mockArtist.setId("6l3HvQ5sa6mXTsMTB19rO5");
        mockArtist.setType("artist");
        mockArtist.setExternalUrls(externalUrls);
        mockTrack.setArtists(List.of(mockArtist));

        Image mockImage = new Image();
        mockImage.setHeight(640);
        mockImage.setUrl("https://i.scdn.co/image/ab67616d0000b27399da48a530f6ca2ef86da3cc");
        mockImage.setWidth(640);
        mockAlbum.setImages(List.of(mockImage));

        mockTrackDto.setName("Work Out");
        mockTrackDto.setId("2wAJTrFhCnQyNSD3oUgTZO");
        mockTrackDto.setType("track");
//        mockTrackDto.setUri("spotify:track:2wAJTrFhCnQyNSD3oUgTZO");
        mockTrackDto.setExternalUrls(externalUrls);

        wishlistDto.setUsername("jagadeep");
        wishlistDto.setTracks(List.of(mockTrackDto));
        wishlist.setUsername("jagadeep");
        wishlist.setTracks(List.of(track));
        wishlistRepository.save(wishlist);
    }

    @AfterEach
    void deleteRepo(){
        wishlistRepository.deleteAll();
    }


    @Test
    void getUserWishlist_UserExists_ShouldReturnWishListDto() {
        // Arrange


        Wishlist wishlist1 = new Wishlist();
        wishlist1.setUsername("jagadeep");
//        wishlist1.setTracks(List.of());
        System.out.println(wishlist.getUsername());
        wishlistRepository.save(wishlist1);
        when(wishlistRepository.findById("jagadeep")).thenReturn(Optional.of(wishlist));
//        when(modelMapper.map(wishlist, WishlistDto.class)).thenReturn(wishlistDto);

        // Act
        WishlistDto resultDto = wishlistService.getUserWishlist("jagadeep");

        // Assert
        assertNotNull(resultDto);
        assertEquals(wishlistDto, resultDto);
    }

    @Test
    void getUserWishlist_UserDoesNotExist_ShouldThrowResourceNotFoundException() {
        // Arrange
        String username = "nonexistentUser";

        when(wishlistRepository.findById(username)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> wishlistService.getUserWishlist(username));
    }

    @Test
    void saveTrackToWishlist_UserExists_ShouldAddOrUpdateTrack() {
//        String username = "testUser";
//        TrackDto trackDto = modelMapper.map(track, TrackDto.class);
//
//        Wishlist existingWishlist = new Wishlist();
//        existingWishlist.setUsername(username);

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
    void saveTrackToWishlist_UserDoesNotExist_ShouldCreateNewWishlist() {
        // Arrange
        String username = "testUser";
        TrackDto trackDto = new TrackDto(/* provide track details */);

        when(wishlistRepository.findById(username)).thenReturn(Optional.empty());

        // Act
        TrackDto savedTrack = wishlistService.saveTrackToWishlist(username, trackDto);

        // Assert
        verify(wishlistRepository, times(1)).save(any(Wishlist.class));
        // Additional assertions based on your business logic and expectations
    }

    @Test
    void deleteTrackByUsernameAndTrackId_UserExistsAndTrackExists_ShouldDeleteTrack() {
        // Arrange
//        String username = "testUser";
//        String trackIdToDelete = "track123";
//        Track trackToDelete = new Track();
//        trackToDelete.setId(trackIdToDelete);
//        Wishlist mockWishlist = new Wishlist();
//        mockWishlist.setUsername(username);
//        mockWishlist.setTracks(List.of(trackToDelete));
//
//        when(wishlistRepository.findById(username)).thenReturn(Optional.of(mockWishlist));
//
//         Act
//        String result = wishlistService.deleteTrackByUsernameAndTrackId(username, mockTrackDto);
//
//         Assert
//        verify(wishlistRepository, times(1)).save(mockWishlist);
//        assertEquals("Track with Id: " + trackIdToDelete + " deleted.", result);
    }

    @Test
    void deleteTrackByUsernameAndTrackId_UserDoesNotExist_ShouldThrowResourceNotFoundException() {
        // Arrange
        String username = "nonexistentUser";
        String trackIdToDelete = "track123";

        when(wishlistRepository.findById(username)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> wishlistService.deleteTrackByUsernameAndTrackId(username, trackIdToDelete));
    }

    @Test
    void deleteTrackByUsernameAndTrackId_UserExistsButTrackDoesNotExist_ShouldNotDeleteTrack() {
        // Arrange
        String username = "jagadeep";
        String trackIdToDelete = "nonexistentTrack";
        Track track = new Track();
        track.setId("track123");
        Wishlist mockWishlist = new Wishlist();
        mockWishlist.setUsername(username);
        mockWishlist.setTracks(List.of(track));

        wishlistRepository.save(wishlist);
        when(wishlistRepository.findById(username)).thenReturn(Optional.of(mockWishlist));

        // Act
        String result = wishlistService.deleteTrackByUsernameAndTrackId(username, trackIdToDelete);

        // Assert
        verify(wishlistRepository, never()).save(any());
        assertEquals("Track with Id: " + trackIdToDelete + " not found in wishlist.", result);
    }
}