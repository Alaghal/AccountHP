package com.homeproducts.homeacc.query.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Import(AxonConfig.class)
@EntityScan(basePackages = "com.homeproducts.homeacc.core.models")
@SpringBootApplication
public class HomeAccQueryApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomeAccQueryApplication.class, args);
	}

}
