package com.spotify.musicservice.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Optional;

import com.spotify.musicservice.model.SpotifyAccessToken;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith({SpringExtension.class, MockitoExtension.class})
@DataJpaTest
public class SpotifyAccessTokenRepositoryTest {

    @Mock
    private SpotifyAccessTokenRepository repository;

    // You might have a service class that uses the repository

    @Test
    public void testFindById() {
        // Mock data
        int id = 1;
        SpotifyAccessToken accessToken = new SpotifyAccessToken(id,"jjj","jjj","kkk");
        repository.save(accessToken);
        // Mock the repository behavior
        when(repository.findById(id)).thenReturn(Optional.of(accessToken));

        // Perform the test
        Optional<SpotifyAccessToken> result = repository.findById(id); // Assuming you have a findById method in your service

        // Verify the result
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(accessToken);
    }
}
