package oz.bookiarybacked.presentation.api;

import java.net.URI;

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
	 * 소셜 로그인 페이지로 리다이렉트하는 메서드 (ex. 카카오 로그인 페이지)
	 * @param provider OAuth 제공자
	 *  - 카카오: /api/oauth/kakao (🔗: https://developers.kakao.com/docs/latest/ko/kakaologin/rest-api)
	 */
	@GetMapping("/oauth/{provider}")
	public ResponseEntity<ApiResult<Void>> getLoginPage(
		@PathVariable String provider
	) {
		URI redirectUri = authService.getLoginUrl(provider);
		ApiResult<Void> result = ApiResult.of(HttpStatus.FOUND);

		return ResponseEntity
			.status(HttpStatus.FOUND)
			.location(redirectUri)
			.body(result);
	}

	/**
	 * 로그인 요청을 처리하는 메서드
	 * @param provider OAuth 제공자
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
	 * 액세스 토큰 재발급을 처리하는 메서드 (자동 로그인)
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
