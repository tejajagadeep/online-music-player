package com.spotify.wishlistservice.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WishListConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
