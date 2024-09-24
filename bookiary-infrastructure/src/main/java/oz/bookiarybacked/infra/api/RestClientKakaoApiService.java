package oz.bookiarybacked.infra.api;

import static org.springframework.http.MediaType.*;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import lombok.RequiredArgsConstructor;
import oz.bookiarybacked.infra.auth.oauth.kakao.KakaoApiService;
import oz.bookiarybacked.infra.auth.oauth.kakao.dto.TokenInfo;
import oz.bookiarybacked.infra.auth.oauth.kakao.dto.TokenRequestBody;
import oz.bookiarybacked.infra.auth.oauth.kakao.dto.TokenResponse;

@Service
@RequiredArgsConstructor
public class RestClientKakaoApiService implements KakaoApiService {
	private static final String AUTHORIZATION = "Authorization";
	private static final String TOKEN_PREFIX = "Bearer ";
	private static final RestClient REST_CLIENT = RestClient.create();

	@Override
	public TokenResponse sendAccessTokenRequest(TokenRequestBody body, String tokenRequestBaseUri) {
		return REST_CLIENT.post()
			.uri(tokenRequestBaseUri)
			.contentType(APPLICATION_FORM_URLENCODED)
			.body(body.toMap())
			.retrieve()
			.body(TokenResponse.class);
	}

	@Override
	public TokenInfo sendTokenInfoRequest(String accessToken, String tokenInfoRequestUri) {
		return REST_CLIENT.get()
			.uri(tokenInfoRequestUri)
			.header(AUTHORIZATION, TOKEN_PREFIX + accessToken)
			.retrieve()
			.body(TokenInfo.class);
	}
}
