package oz.bookiarybacked.auth.infra.oauth.kakao;

import oz.bookiarybacked.auth.infra.oauth.kakao.dto.AccessTokenInfo;
import oz.bookiarybacked.auth.infra.oauth.kakao.dto.AccessTokenReqBody;
import oz.bookiarybacked.auth.infra.oauth.kakao.dto.AccessTokenRes;

public interface KakaoApiService {
	AccessTokenRes sendAccessTokenRequest(AccessTokenReqBody body, String uri);

	AccessTokenInfo sendTokenInfoRequest(String accessToken, String tokenInfoRequestUri);
}
