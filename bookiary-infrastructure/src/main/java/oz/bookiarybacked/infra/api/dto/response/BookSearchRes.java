package oz.bookiarybacked.infra.api.dto.response;

import java.util.List;

import oz.bookiarybacked.application.common.dto.Page;
import oz.bookiarybacked.domain.book.dto.BookDto;

public record BookSearchRes(
	String lastBuildDate,
	int total,
	int start,
	int display,
	List<BookItem> items
) {
	public Page<BookDto> toPage() {
		List<BookDto> contents = items.stream()
			.map(BookItem::toBookDto)
			.toList();

		return Page.of(total, start, display, contents);
	}
}
