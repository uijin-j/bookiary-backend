package oz.bookiarybacked.auth.infra.api;

import static org.springframework.http.MediaType.*;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import lombok.RequiredArgsConstructor;
import oz.bookiarybacked.auth.infra.oauth.kakao.KakaoApiService;
import oz.bookiarybacked.auth.infra.oauth.kakao.dto.AccessTokenInfo;
import oz.bookiarybacked.auth.infra.oauth.kakao.dto.AccessTokenReqBody;
import oz.bookiarybacked.auth.infra.oauth.kakao.dto.AccessTokenRes;

@Service
@RequiredArgsConstructor
public class RestClientKakaoApiService implements KakaoApiService {
	private static final String AUTHORIZATION = "Authorization";
	private static final String TOKEN_PREFIX = "Bearer ";
	private static final RestClient REST_CLIENT = RestClient.create();

	@Override
	public AccessTokenRes sendAccessTokenRequest(AccessTokenReqBody body, String tokenRequestBaseUri) {
		return REST_CLIENT.post()
			.uri(tokenRequestBaseUri)
			.contentType(APPLICATION_FORM_URLENCODED)
			.body(body.toMap())
			.retrieve()
			.body(AccessTokenRes.class);
	}

	@Override
	public AccessTokenInfo sendTokenInfoRequest(String accessToken, String tokenInfoRequestUri) {
		return REST_CLIENT.get()
			.uri(tokenInfoRequestUri)
			.header(AUTHORIZATION, TOKEN_PREFIX + accessToken)
			.retrieve()
			.body(AccessTokenInfo.class);
	}
}
