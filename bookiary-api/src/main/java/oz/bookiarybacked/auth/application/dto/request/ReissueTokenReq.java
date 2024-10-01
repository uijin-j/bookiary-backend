package oz.bookiarybacked.auth.application.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "토큰 재발급 요청")
public record ReissueTokenReq(
	@Schema(description = "리프레시 토큰", example = "eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJib29raWFyeSIsImlhdCI6MTcyNzYzMzIwOSwiZXhwIjoxNzI3NjM2ODA5LCJ1c2VyX2lkIjoxfQ.uKqgeqLvVdlKRrtYO6hY788OydA_ofZJoWgt_FxSjUNknmAFuGI9C2pIK8NL8PZ5QVb8OKrmJzZ1uZx7H2YsyA")
	String refreshToken
) {
}
