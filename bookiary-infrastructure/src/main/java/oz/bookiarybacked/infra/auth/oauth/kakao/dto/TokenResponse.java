package oz.bookiarybacked.infra.auth.oauth.kakao.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TokenResponse(
	@JsonProperty("token_type")
	String tokenType,
	@JsonProperty("access_token")
	String accessToken,
	@JsonProperty("id_token")
	String idToken,
	@JsonProperty("expires_in")
	int expiresIn,
	@JsonProperty("refresh_token")
	String refreshToken,
	@JsonProperty("refresh_token_expires_in")
	int refreshTokenExpiresIn,
	@JsonProperty("scope")
	String scope
) {
}
