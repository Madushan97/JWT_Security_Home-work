package com.bezkoder.springjwt.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;

public class SwaggerConfig {

    @Configuration
    @OpenAPIDefinition(servers = {@Server(url = "${swagger.server.url}")}) // add the context path here
    public class SwaggerConfiguration {
        @Bean
        public OpenAPI customizeOpenAPI() {
            final String securitySchemeName = "bearerAuth";
            return new OpenAPI()
                    .addSecurityItem(new SecurityRequirement()
                            .addList(securitySchemeName))
                    .components(new Components()
                            .addSecuritySchemes(securitySchemeName, new SecurityScheme()
                                    .name(securitySchemeName)
                                    .type(SecurityScheme.Type.HTTP)
                                    .scheme("bearer")
                                    .bearerFormat("JWT")))
                    .info(new Info()
                            .title("Test_Security")
                            .version("1.0.0")
                            .description("Test_Security")
                            .contact(new Contact().name("Test Security System")));
        }
    }
}
