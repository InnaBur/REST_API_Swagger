package com.spring.firstSpring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.info.InfoContributorAutoConfiguration;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Import;

@Configuration
@Import(InfoContributorAutoConfiguration.class)
public class OpenApiConfig {
    @Value("${info.myapp.version}")
    private String appVersion;

    @Bean
    public OpenAPI usersMicroserviceOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Persons API")
                        .description("First Spring lab")
                        .version(appVersion));
    }

}
