package oz.bookiarybacked.auth.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("oauth.kakao")
public record KakaoOAuthProperties(
	String authorizationCodeRequestUri,
	String tokenRequestUri,
	String clientId,
	String redirectUri,
	String tokenInfoRequestUri,
	String clientSecret
) {
}
