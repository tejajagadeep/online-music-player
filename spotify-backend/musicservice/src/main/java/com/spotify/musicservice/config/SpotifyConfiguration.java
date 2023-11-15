package com.spotify.musicservice.config;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.SpotifyHttpManager;

//@Service
@Configuration
public class SpotifyConfiguration {
	
//	@Value("${redirect.server.ip}")
	private String customIp="http://localhost:8083";
	
	public SpotifyApi getSpotifyObject() {
		 URI redirectedURL =  SpotifyHttpManager.makeUri(customIp + "/api/get-user-code/");
		 
		 return new SpotifyApi
				 .Builder()
				 .setClientId("3239b84423974bb690cba3b46a6c3f4a")
				 .setClientSecret("af2c99af4b92432cb364f5f89c98e6fa")
				 .setRedirectUri(redirectedURL)
				 .build();
	}
}
