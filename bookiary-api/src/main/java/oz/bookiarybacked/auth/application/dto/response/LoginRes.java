package oz.bookiarybacked.auth.application.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import oz.bookiarybacked.auth.application.dto.TokenPair;

@Builder(access = AccessLevel.PRIVATE)
@Schema(description = "로그인 응답")
public record LoginRes(
	@Schema(description = "액세스 토큰", example = "eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJib29raWFyeSIsImlhdCI6MTcyNzYzMzIwOSwiZXhwIjoxNzI3NjM2ODA5LCJ1c2VyX2lkIjoxfQ.uKqgeqLvVdlKRrtYO6hY788OydA_ofZJoWgt_FxSjUNknmAFuGI9C2pIK8NL8PZ5QVb8OKrmJzZ1uZx7H2YsyA")
	String accessToken,
	@Schema(description = "액세스 토큰", example = "eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJib29raWFyeSIsImlhdCI6MTcyNzYzMzIwOSwiZXhwIjoxNzI3NjM2ODA5LCJ1c2VyX2lkIjoxfQ.uKqgeqLvVdlKRrtYO6hY788OydA_ofZJoWgt_FxSjUNknmAFuGI9C2pIK8NL8PZ5QVb8OKrmJzZ1uZx7H2YsyA")
	String refreshToken
) {
	public static LoginRes from(TokenPair tokenPair) {
		return LoginRes.builder()
			.accessToken(tokenPair.accessToken())
			.refreshToken(tokenPair.refreshToken())
			.build();
	}
}
