package dev.subrotokumar.accounts.config;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@OpenAPIDefinition(
	info = @Info(
		title = "DxD: Account Microservice REST API Documentation",
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
		description = "DxD Account Microservice REST API Docs",
		url = "http://blog.subrotokumar.com"
	)
)
@SecurityScheme(
	name = "bearerAuth",
	description = "JWT Auth description",
	scheme = "bearer",
	type = SecuritySchemeType.HTTP,
	bearerFormat = "JWT",
	in = SecuritySchemeIn.HEADER
)
public class SwaggerConfig {
    
}
