package oz.bookiarybacked.domain.bookshelf.domain.dto;

import java.time.LocalDate;

public record BookDetailDto(
	Long id,
	String title,
	String imageUrl,
	String author,
	String publisher,
	LocalDate publishedAt,
	String description
) {
}
