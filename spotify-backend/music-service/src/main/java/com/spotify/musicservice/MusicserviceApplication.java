package com.spotify.musicservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableDiscoveryClient
@EnableScheduling
@OpenAPIDefinition(info = @Info(title = "MUSIC-SERVICE API", version = "1.0", description = "Music Information"))
public class MusicserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MusicserviceApplication.class, args);
	}

}
