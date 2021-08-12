package com.homeproducts.homeacc.cmd.api;

import com.homeproducts.homeacc.core.configuration.AxonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;


@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Import(AxonConfig.class)
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class HomeAccCommandApplication {

    public static void main(String[] args) {
        SpringApplication.run(HomeAccCommandApplication.class, args);
    }

}
