package oz.bookiarybacked.infra.auth.oauth.kakao;

import static oz.bookiarybacked.domain.auth.model.AuthProviderType.*;

import java.net.URI;

import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import oz.bookiarybacked.config.properties.KakaoOAuthProperties;
import oz.bookiarybacked.domain.auth.dto.OAuthUser;
import oz.bookiarybacked.domain.auth.model.AuthProviderType;
import oz.bookiarybacked.domain.auth.service.OAuthManager;
import oz.bookiarybacked.infra.auth.oauth.kakao.dto.AccessTokenInfo;
import oz.bookiarybacked.infra.auth.oauth.kakao.dto.AccessTokenReqBody;
import oz.bookiarybacked.infra.auth.oauth.kakao.dto.AccessTokenRes;

@Component
public class KakaoOAuthManager implements OAuthManager {
	private static final String AUTHORIZATION_CODE = "authorization_code";
	private final KakaoApiService kaKaoApiService;
	private final String authorizationCodeRequestUri;
	private final String tokenRequestUri;
	private final String tokenInfoRequestUri;
	private final String clientId;
	private final String redirectUri;
	private final String clientSecret;

	public KakaoOAuthManager(KakaoApiService kakaoApiService, KakaoOAuthProperties properties) {
		this.kaKaoApiService = kakaoApiService;
		this.authorizationCodeRequestUri = properties.authorizationCodeRequestUri();
		this.tokenRequestUri = properties.tokenRequestUri();
		this.clientId = properties.clientId();
		this.redirectUri = properties.redirectUri();
		this.tokenInfoRequestUri = properties.tokenInfoRequestUri();
		this.clientSecret = properties.clientSecret();
	}

	@Override
	public AuthProviderType getType() {
		return KAKAO;
	}

	/**
	 * 카카오 로그인 페이지 URL 생성
	 * cf) https://developers.kakao.com/docs/latest/ko/kakaologin/rest-api#request-code
	 */
	@Override
	public URI getLoginPageUrl() {
		return UriComponentsBuilder
			.fromUriString(authorizationCodeRequestUri)
			.queryParam("response_type", "code")
			.queryParam("client_id", clientId)
			.queryParam("redirect_uri", redirectUri)
			.build()
			.toUri();
	}

	/**
	 * 액세스 토큰(AT) 요청
	 * cf) https://developers.kakao.com/docs/latest/ko/kakaologin/rest-api#request-token
	 */
	@Override
	public OAuthUser retrieveOAuthUser(String code) {
		// Step 1. 인증 서버에서 AT 발급
		AccessTokenRes response = kaKaoApiService.sendAccessTokenRequest(buildTokenRequestBody(code), tokenRequestUri);
		String accessToken = response.accessToken();

		// Step 2. AT로 사용자 정보 요청
		AccessTokenInfo accessTokenInfo = kaKaoApiService.sendTokenInfoRequest(accessToken, tokenInfoRequestUri);
		return OAuthUser.of(KAKAO, String.valueOf(accessTokenInfo.memberId()));
	}

	private AccessTokenReqBody buildTokenRequestBody(String code) {
		return AccessTokenReqBody.builder()
			.grantType(AUTHORIZATION_CODE)
			.clientId(clientId)
			.redirectUri(redirectUri)
			.code(code)
			.clientSecret(clientSecret)
			.build();
	}

	private Claims parseJwt(String token) {
		return Jwts.parserBuilder()
			.build()
			.parseClaimsJws(token)
			.getBody();
	}
}
