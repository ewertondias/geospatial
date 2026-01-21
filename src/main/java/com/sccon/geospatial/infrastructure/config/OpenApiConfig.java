package com.sccon.geospatial.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpeAPI() {
        var info = new Info()
            .title("Geospatial API")
            .description("This is the Geospatial API")
            .version("1.0.0");

        return new OpenAPI()
            .info(info);
    }

}
