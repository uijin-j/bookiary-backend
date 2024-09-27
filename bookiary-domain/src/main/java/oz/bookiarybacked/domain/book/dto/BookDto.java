package oz.bookiarybacked.domain.book.dto;

import lombok.Builder;

@Builder
public record BookDto(
	String title,
	String link,
	String imageUrl,
	String author,
	String price,
	String publisher,
	String publishedAt,
	String isbn,
	String description
) {
}
