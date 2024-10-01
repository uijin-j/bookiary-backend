package oz.bookiarybacked.common.presentation.dto;

import static org.springframework.http.HttpStatus.*;

import org.springframework.http.HttpStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import oz.bookiarybacked.auth.application.dto.response.LoginPageRes;
import oz.bookiarybacked.auth.application.dto.response.LoginRes;
import oz.bookiarybacked.common.dto.Page;
import oz.bookiarybacked.domain.bookshelf.domain.dto.response.RetrieveBookRes;

/**
 * API 응답을 표준화하기 위한 제네릭 레코드(Record)
 *
 * 이 클래스는 API 응답에서 반환되는 상태 코드, 상태 메시지, 응답 데이터 등을 포함하여
 * 일관된 응답 형식을 제공하기 위해 설계되었습니다.
 */
@Schema(description = "API 응답")
public record ApiResult<T>(
	@Schema(description = "상태 코드", example = "200")
	int code,
	@Schema(description = "HTTP 상태 코드", example = "OK")
	HttpStatus status,
	@Schema(description = "메시지", example = "성공")
	String message,
	@Schema(description = "응답 데이터", oneOf = {Void.class, LoginPageRes.class, LoginRes.class, Page.class,
		RetrieveBookRes.class})
	T data
) {
	private ApiResult(HttpStatus status, String message, T data) {
		this(status.value(), status, message, data);
	}

	public static <T> ApiResult<T> of(HttpStatus httpStatus, String message, T data) {
		return new ApiResult<>(httpStatus, message, data);
	}

	public static <T> ApiResult<T> of(HttpStatus httpStatus, T data) {
		return of(httpStatus, httpStatus.name(), data);
	}

	public static ApiResult<Void> of(HttpStatus httpStatus) {
		return new ApiResult<>(httpStatus, httpStatus.name(), null);
	}

	public static <T> ApiResult<T> ok(T data) {
		return of(OK, data);
	}

	public static <T> ApiResult<T> error(HttpStatus httpStatus, String message) {
		return new ApiResult<>(httpStatus, message, null);
	}
}
