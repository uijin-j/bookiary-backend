package oz.bookiarybacked.auth.api;

import static oz.bookiarybacked.auth.api.AuthResponseExamples.*;

import org.springframework.http.ResponseEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import oz.bookiarybacked.auth.application.dto.request.ReissueTokenReq;
import oz.bookiarybacked.auth.application.dto.response.LoginPageRes;
import oz.bookiarybacked.auth.application.dto.response.LoginRes;
import oz.bookiarybacked.auth.domain.model.AuthProviderType;
import oz.bookiarybacked.common.presentation.dto.ApiResult;

@Tag(name = "Auth", description = "인증 관련 API")
public interface AuthApiDoc {
	@Operation(summary = "소셜 로그인 페이지 조회")
	@ApiResponse(responseCode = "200", description = "OK", content = {
		@Content(schema = @Schema(implementation = ApiResult.class, oneOf = {LoginPageRes.class}),
			examples = {@ExampleObject(name = "200", value = LOGIN_PAGE_200_RESPONSE)}
		)},
		headers = {
			@Header(
				name = "Cache-Control",
				description = "캐시 제어 헤더",
				required = true,
				schema = @Schema(type = "string")
			)}
	)
	@Parameter(
		name = "provider",
		in = ParameterIn.PATH,
		schema = @Schema(
			implementation = AuthProviderType.class,
			defaultValue = "KAKAO"
		)
	)
	ResponseEntity<ApiResult<LoginPageRes>> getLoginPage(String provider);

	@Operation(summary = "소셜 로그인")
	@ApiResponse(responseCode = "200", description = "OK", content = {
		@Content(schema = @Schema(implementation = ApiResult.class, oneOf = {LoginRes.class}),
			examples = {@ExampleObject(name = "200", value = LOGIN_200_RESPONSE)})
	})
	@Parameters({
		@Parameter(
			name = "provider",
			in = ParameterIn.PATH,
			schema = @Schema(
				implementation = AuthProviderType.class,
				defaultValue = "KAKAO"
			)
		),
		@Parameter(
			name = "code",
			description = "OAuth 인증 코드",
			in = ParameterIn.QUERY,
			required = true
		)
	})
	ResponseEntity<ApiResult<LoginRes>> socialLogin(String provider, String code);

	@Operation(summary = "토큰 재발급")
	@ApiResponse(responseCode = "200", description = "OK", content = {
		@Content(schema = @Schema(implementation = ApiResult.class, oneOf = {LoginRes.class}),
			examples = {@ExampleObject(name = "200", value = LOGIN_200_RESPONSE)})
	})
	ResponseEntity<ApiResult<LoginRes>> reissue(ReissueTokenReq request);
}
