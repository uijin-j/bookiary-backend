package oz.bookiarybacked.exception;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ErrorMessages {
	public static final String EXPIRED_TOKEN = "만료된 토큰입니다.";
	public static final String INVALID_TOKEN = "유효하지 않은 토큰입니다.";
	public static final String UNSUPPORTED_OAUTH_TYPE = "지원하지 않는 소셜 로그인 타입입니다.";
}
