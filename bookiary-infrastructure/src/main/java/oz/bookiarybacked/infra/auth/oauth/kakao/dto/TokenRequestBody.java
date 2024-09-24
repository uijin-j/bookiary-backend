package oz.bookiarybacked.infra.auth.oauth.kakao.dto;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import lombok.Builder;

@Builder
public record TokenRequestBody(
	String grantType,
	String clientId,
	String redirectUri,
	String code,
	String clientSecret
) {

	public MultiValueMap<String, String> toMap() {
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("grant_type", grantType);
		map.add("client_id", clientId);
		map.add("redirect_uri", redirectUri);
		map.add("code", code);
		map.add("client_secret", clientSecret);
		return map;
	}
}
