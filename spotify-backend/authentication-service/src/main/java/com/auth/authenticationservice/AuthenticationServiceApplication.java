package com.auth.authenticationservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@OpenAPIDefinition(info = @Info(title = "Authentication-SERVICE API", version = "1.0", description = "Authenticator for spotify app"))
public class AuthenticationServiceApplication {

	public static void main(String[] args) {

		SpringApplication.run(AuthenticationServiceApplication.class, args);
	}

}
