package oz.bookiarybacked.infra.auth.oauth.kakao.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TokenInfo(
	@JsonProperty("id")
	long memberId,
	@JsonProperty("expires_in")
	int expiresIn,
	@JsonProperty("app_id")
	int appId
) {
}
