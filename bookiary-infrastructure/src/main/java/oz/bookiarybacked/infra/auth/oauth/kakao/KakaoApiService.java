package oz.bookiarybacked.infra.auth.oauth.kakao;

import oz.bookiarybacked.infra.auth.oauth.kakao.dto.AccessTokenInfo;
import oz.bookiarybacked.infra.auth.oauth.kakao.dto.AccessTokenReqBody;
import oz.bookiarybacked.infra.auth.oauth.kakao.dto.AccessTokenRes;

public interface KakaoApiService {
	AccessTokenRes sendAccessTokenRequest(AccessTokenReqBody body, String uri);

	AccessTokenInfo sendTokenInfoRequest(String accessToken, String tokenInfoRequestUri);
}
