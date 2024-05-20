package dev.subrotokumar.project.config;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@OpenAPIDefinition(
        info = @Info(
                title = "DxD: Project Microservice REST API Documentation",
                version = "v1",
                contact = @Contact(
                        name = "Subroto Kumar",
                        url = "https://github.com/subrotokumar"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "http://github.com/subrotokumar/dxd-paas"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "DxD Project Microservice REST API Docs",
                url = "http://blog.subrotokumar.com"
        )
)
public class SwaggerConfig {
}
