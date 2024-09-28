package oz.bookiarybacked.application.book.dto.request;

import static java.util.Objects.*;
import static oz.bookiarybacked.exception.ErrorMessages.*;

import lombok.Getter;

@Getter
public final class BookSearchParam {
	private static final int DEFAULT_START = 1;
	private static final int DEFAULT_SIZE = 10;

	private final String keyword;
	private final int start;
	private final int size;

	public BookSearchParam(String keyword, Integer start, Integer size) {
		validateKeyword(keyword);

		this.keyword = keyword;
		this.start = isNull(start) ? DEFAULT_START : start;
		this.size = isNull(size) ? DEFAULT_SIZE : size;
	}

	private void validateKeyword(String keyword) {
		if (isNull(keyword) || keyword.isBlank()) {
			throw new IllegalArgumentException(KEYWORD_REQUIRED);
		}
	}
}
