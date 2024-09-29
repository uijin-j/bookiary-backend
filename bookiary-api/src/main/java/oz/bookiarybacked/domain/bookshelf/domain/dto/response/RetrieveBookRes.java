package oz.bookiarybacked.domain.bookshelf.domain.dto.response;

import java.time.LocalDate;
import java.util.List;

import oz.bookiarybacked.domain.bookshelf.domain.dto.BookDetailDto;
import oz.bookiarybacked.domain.bookshelf.domain.dto.NoteDto;

public record RetrieveBookRes(
	Long id,
	String title,
	String imageUrl,
	String author,
	String publisher,
	LocalDate publishedAt,
	String description,
	List<NoteDto> notes
) {

	public static RetrieveBookRes of(BookDetailDto bookInfo, List<NoteDto> notes) {
		return new RetrieveBookRes(
			bookInfo.id(),
			bookInfo.title(),
			bookInfo.imageUrl(),
			bookInfo.author(),
			bookInfo.publisher(),
			bookInfo.publishedAt(),
			bookInfo.description(),
			notes
		);
	}
}
