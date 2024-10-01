package oz.bookiarybacked.auth.application.dto.response;

import java.net.URI;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
@Schema(description = "로그인 페이지 응답")
public record LoginPageRes(
	@Schema(description = "로그인 페이지 URL", example = "/login")
	String loginPageUrl
) {
	public static LoginPageRes from(URI loginPageUrl) {
		return LoginPageRes.builder()
			.loginPageUrl(loginPageUrl.toString())
			.build();
	}
}
