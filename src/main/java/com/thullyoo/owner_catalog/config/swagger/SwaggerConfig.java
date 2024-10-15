package com.thullyoo.owner_catalog.config.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    OpenAPI openAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("Catalog API")
                        .version("1.0")
                        .description("API for challenge anota ai")
                        .contact(new Contact()
                                .email("thullyocontact@gmail.com")
                                .name("Thúllyo Barcellos")
                        )
                );
    }
}
