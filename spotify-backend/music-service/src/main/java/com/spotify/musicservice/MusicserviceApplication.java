package com.spotify.musicservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MusicserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MusicserviceApplication.class, args);
	}

}
