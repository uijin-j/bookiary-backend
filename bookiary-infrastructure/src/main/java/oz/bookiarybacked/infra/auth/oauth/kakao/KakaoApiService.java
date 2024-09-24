package oz.bookiarybacked.infra.auth.oauth.kakao;

import oz.bookiarybacked.infra.auth.oauth.kakao.dto.TokenInfo;
import oz.bookiarybacked.infra.auth.oauth.kakao.dto.TokenRequestBody;
import oz.bookiarybacked.infra.auth.oauth.kakao.dto.TokenResponse;

public interface KakaoApiService {
	TokenResponse sendAccessTokenRequest(TokenRequestBody body, String uri);

	TokenInfo sendTokenInfoRequest(String accessToken, String tokenInfoRequestUri);
}
