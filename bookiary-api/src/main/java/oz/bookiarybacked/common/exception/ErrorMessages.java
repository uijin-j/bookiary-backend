package oz.bookiarybacked.exception;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ErrorMessages {
	// User
	public static final String USER_NOT_FOUND = "해당 식별자를 가진 사용자가 존재하지 않습니다.";

	// Book
	public static final String KEYWORD_REQUIRED = "검색어는 필수값입니다.";

	// Common
	public static final String PERMISSION_DENIED = "권한이 없습니다.";
}
