package oz.bookiarybacked.auth.application.dto.response;

import lombok.Builder;
import oz.bookiarybacked.auth.application.dto.TokenPair;

@Builder
public record LoginRes(
	String accessToken,
	String refreshToken
) {
	public static LoginRes from(TokenPair tokenPair) {
		return LoginRes.builder()
			.accessToken(tokenPair.accessToken())
			.refreshToken(tokenPair.refreshToken())
			.build();
	}
}
