package oz.bookiarybacked.domain.book.application.dto.request;

import static java.util.Objects.*;
import static oz.bookiarybacked.common.exception.ErrorMessages.*;

import lombok.Getter;
import oz.bookiarybacked.common.dto.PageParam;

@Getter
public final class BookSearchParam {
	private final String keyword;
	private final PageParam pageParam;

	public BookSearchParam(String keyword, Integer start, Integer size) {
		validateKeyword(keyword);

		this.keyword = keyword;
		this.pageParam = new PageParam(start, size);
	}

	public int getSize() {
		return pageParam.getSize();
	}

	public int getStart() {
		return pageParam.getStart();
	}

	private void validateKeyword(String keyword) {
		if (isNull(keyword) || keyword.isBlank()) {
			throw new IllegalArgumentException(KEYWORD_REQUIRED);
		}
	}
}
