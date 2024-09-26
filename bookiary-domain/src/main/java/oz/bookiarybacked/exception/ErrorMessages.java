package oz.bookiarybacked.exception;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ErrorMessages {
	// Auth
	public static final String EXPIRED_TOKEN = "만료된 토큰입니다.";
	public static final String INVALID_TOKEN = "유효하지 않은 토큰입니다.";
	public static final String UNSUPPORTED_OAUTH_TYPE = "지원하지 않는 소셜 로그인 타입입니다.";
	public static final String LOGIN_REQUIRED = "로그인이 필요한 요청입니다.";

	// User
	public static final String USER_NOT_FOUND = "해당 식별자를 가진 사용자가 존재하지 않습니다.";

	// Book
	public static final String KEYWORD_REQUIRED = "검색어는 필수값입니다.";
}
