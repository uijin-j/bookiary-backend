package oz.bookiarybacked.auth.infra.oauth.kakao.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AccessTokenInfo(
	@JsonProperty("id")
	long memberId,
	@JsonProperty("expires_in")
	int expiresIn,
	@JsonProperty("app_id")
	int appId
) {
}
