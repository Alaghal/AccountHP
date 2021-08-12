package com.homeproducts.homeacc.query.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Import(AxonConfig.class)
@SpringBootApplication
public class HomeAccQueryApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomeAccQueryApplication.class, args);
	}

}
