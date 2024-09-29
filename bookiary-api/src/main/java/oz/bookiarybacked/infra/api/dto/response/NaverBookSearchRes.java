package oz.bookiarybacked.infra.api.dto.response;

import java.util.List;

import oz.bookiarybacked.common.dto.Page;
import oz.bookiarybacked.domain.book.domain.dto.BookDto;

public record NaverBookSearchRes(
	String lastBuildDate,
	int total,
	int start,
	int display,
	List<NaverBookItem> items
) {
	public Page<BookDto> toPage() {
		List<BookDto> contents = items.stream()
			.map(NaverBookItem::toBookDto)
			.toList();

		return Page.of(total, start, display, contents);
	}
}
