package oz.bookiarybacked.common.exception;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ErrorMessages {
	// User
	public static final String USER_NOT_FOUND = "해당 식별자를 가진 사용자가 존재하지 않습니다.";

	// Book
	public static final String KEYWORD_REQUIRED = "검색어는 필수값입니다.";

	// UserBook
	public static final String USER_BOOK_NOT_FOUND = "해당 식별자를 가진 책이 존재하지 않습니다.";
	public static final String USER_BOOK_ALREADY_EXISTS = "이미 책장에 추가된 책입니다.";

	// Note
	public static final String CONTENT_REQUIRED = "메모의 내용은 필수값입니다.";
	public static final String NOTE_NOT_FOUND = "해당 식별자를 가진 노트가 존재하지 않습니다.";

	// Common
	public static final String USER_BOOK_ID_REQUIRED = "사용자 도서 식별자는 필수값입니다.";
	public static final String PERMISSION_DENIED = "권한이 없습니다.";
}
