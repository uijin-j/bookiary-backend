package oz.bookiarybacked.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {
	@Bean
	public OpenAPI openAPI() {
		String authenticationScheme = "토큰 기반 인증";
		SecurityRequirement securityRequirement = new SecurityRequirement()
			.addList(authenticationScheme);

		Components components = new Components()
			.addSecuritySchemes(authenticationScheme, createAPIKeyScheme());

		return new OpenAPI()
			.info(apiInfo())
			.addSecurityItem(securityRequirement)
			.components(components);
	}

	private Info apiInfo() {
		return new Info()
			.title("Bookiary API") // API의 제목
			.description("책단장 API 명세서 📖") // API에 대한 설명
			.version("v1"); // API의 버전
	}

	private SecurityScheme createAPIKeyScheme() {
		return new SecurityScheme()
			.type(SecurityScheme.Type.HTTP)
			.bearerFormat("JWT")
			.scheme("bearer")
			.in(SecurityScheme.In.HEADER)
			.name("Authorization");
	}

}
