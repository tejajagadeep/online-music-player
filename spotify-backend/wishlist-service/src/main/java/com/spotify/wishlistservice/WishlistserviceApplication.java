package com.spotify.wishlistservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication
public class WishlistserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WishlistserviceApplication.class, args);
	}

}
