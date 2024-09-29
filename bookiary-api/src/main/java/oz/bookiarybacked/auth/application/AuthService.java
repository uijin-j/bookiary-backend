package oz.bookiarybacked.auth.application;

import java.net.URI;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import oz.bookiarybacked.auth.application.dto.TokenPair;
import oz.bookiarybacked.auth.application.dto.request.ReissueTokenReq;
import oz.bookiarybacked.auth.application.dto.response.LoginRes;
import oz.bookiarybacked.auth.domain.dto.OAuthUser;
import oz.bookiarybacked.auth.domain.model.AuthProviderType;
import oz.bookiarybacked.auth.domain.model.RefreshToken;
import oz.bookiarybacked.auth.domain.repository.RefreshTokenRepository;
import oz.bookiarybacked.auth.domain.service.AuthDomainService;
import oz.bookiarybacked.auth.domain.service.JwtManager;
import oz.bookiarybacked.auth.domain.service.OAuthManager;
import oz.bookiarybacked.auth.domain.service.OAuthManagerFactory;
import oz.bookiarybacked.auth.exception.AuthErrorMessages;
import oz.bookiarybacked.auth.exception.TokenValidationFailException;
import oz.bookiarybacked.domain.user.domain.model.User;
import oz.bookiarybacked.domain.user.domain.service.UserDomainService;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthService {
	private final AuthDomainService authDomainService;
	private final UserDomainService userDomainService;
	private final OAuthManagerFactory oAuthManagerFactory;
	private final JwtManager jwtManager;
	private final RefreshTokenRepository refreshTokenRepository;

	public URI getLoginUrl(String providerType) {
		OAuthManager oauthManager = oAuthManagerFactory.getOAuthManager(AuthProviderType.from(providerType));
		return oauthManager.getLoginPageUrl();
	}

	@Transactional
	public LoginRes socialLogin(String providerType, String code) {
		// Step 1. 자원 서버에서 사용자 정보를 가져옴
		OAuthManager oauthManager = oAuthManagerFactory.getOAuthManager(AuthProviderType.from(providerType));
		OAuthUser oAuthUser = oauthManager.retrieveOAuthUser(code);

		// Step 2. 회원 조회 (최초 로그인인 경우 회원가입)
		User user = authDomainService.getUser(oAuthUser);

		// Step 3. 북단장 자체 AT/RT 발급
		return LoginRes.from(createTokenPair(user));
	}

	@Transactional
	public LoginRes reissue(ReissueTokenReq request) {
		// Step 1. Refresh Token 파싱
		String refreshToken = request.refreshToken();
		Long userId = jwtManager.getUserId(refreshToken);

		// Step 2. 기존 Refresh Token 조회
		RefreshToken oldToken = refreshTokenRepository.findByUserId(userId);

		// Step 3. Refresh Token 검증
		oldToken.validate(refreshToken);

		// Step 4. 만료 여부 확인
		if (oldToken.isExpired()) {
			refreshTokenRepository.delete(oldToken);
			throw new TokenValidationFailException(AuthErrorMessages.EXPIRED_TOKEN);
		}

		// Step 4. 기존 Refresh Token 삭제
		refreshTokenRepository.delete(oldToken);

		// Step 5. 사용자 조회
		User user = userDomainService.getById(userId);

		// Step 6. AT/RT 재발급
		return LoginRes.from(createTokenPair(user));
	}

	private TokenPair createTokenPair(User user) {
		String accessToken = jwtManager.generateAccessToken(user);
		String refreshToken = jwtManager.generateRefreshToken(user);

		storeRefreshToken(refreshToken, user.getId());

		return new TokenPair(accessToken, refreshToken);
	}

	private void storeRefreshToken(String token, Long userId) {
		RefreshToken refreshToken = RefreshToken.of(userId, token, jwtManager.getExpiredAt(token));
		refreshTokenRepository.save(refreshToken);
	}
}
