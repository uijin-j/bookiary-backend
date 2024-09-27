package oz.bookiarybacked.presentation.api;

import java.net.URI;
import java.util.concurrent.TimeUnit;

import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import oz.bookiarybacked.application.auth.AuthService;
import oz.bookiarybacked.application.auth.dto.request.ReissueTokenReq;
import oz.bookiarybacked.application.auth.dto.response.LoginRes;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthApiController {

	private final AuthService authService;

	/**
	 * 소셜 로그인 페이지 조회 API (ex. 카카오 로그인 페이지)
	 * @param provider OAuth 제공자
	 *  - 카카오: /api/oauth/kakao (🔗: https://developers.kakao.com/docs/latest/ko/kakaologin/rest-api)
	 */
	@GetMapping("/oauth/{provider}")
	public ResponseEntity<ApiResult<Void>> getLoginPage(
		@PathVariable String provider
	) {
		URI redirectUri = authService.getLoginUrl(provider);
		ApiResult<Void> result = ApiResult.of(HttpStatus.FOUND);
		CacheControl cache = CacheControl.maxAge(30, TimeUnit.DAYS).cachePublic(); // 변경이 거의 발생하지 않기 때문에 캐싱 처리 (1달)

		return ResponseEntity
			.status(HttpStatus.FOUND)
			.cacheControl(cache)
			.location(redirectUri)
			.body(result);
	}

	/**
	 * 로그인 API
	 * @param provider OAuth 제공자 (ex. kakao)
	 * @param code OAuth 인증 코드
	 * @return 로그인 응답 (Access Token / Refresh Token)
	 */
	@PostMapping("/oauth/{provider}/login")
	public ResponseEntity<ApiResult<LoginRes>> socialLogin(
		@PathVariable String provider,
		@RequestParam String code
	) {
		LoginRes response = authService.socialLogin(provider, code);
		ApiResult<LoginRes> result = ApiResult.ok(response);

		return ResponseEntity.ok(result);
	}

	/**
	 * 액세스 토큰 재발급 API (자동 로그인)
	 * @param request 자동 로그인 요청 (Refresh Token)
	 * @return 자동 로그인 응답 (Access Token / Refresh Token)
	 */
	@PostMapping("/auth/reissue")
	public ResponseEntity<ApiResult<LoginRes>> reissue(
		@RequestBody @Valid ReissueTokenReq request
	) {
		LoginRes response = authService.reissue(request);
		ApiResult<LoginRes> result = ApiResult.ok(response);

		return ResponseEntity.ok(result);
	}
}
