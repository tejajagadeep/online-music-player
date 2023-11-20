package com.spotify.musicservice.config;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.SpotifyHttpManager;

//@Service
@Configuration
@RefreshScope
public class SpotifyConfiguration {
	
	@Value("${redirect.server.ip}")
	private String customIp;
	@Value("${spotify.client.id}")
	private String clientId;
	@Value("${spotify.client.secret}")
	private String secret;

	
	public SpotifyApi getSpotifyObject() {
		 URI redirectedURL =  SpotifyHttpManager.makeUri(customIp + "/api/get-user-code/");
		 
		 return new SpotifyApi
				 .Builder()
				 .setClientId(clientId)
				 .setClientSecret(secret)
				 .setRedirectUri(redirectedURL)
				 .build();
	}
}
