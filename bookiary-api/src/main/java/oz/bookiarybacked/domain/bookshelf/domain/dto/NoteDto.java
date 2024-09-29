package oz.bookiarybacked.domain.bookshelf.domain.dto;

import java.time.LocalDateTime;

public record NoteDto(
	Long id,
	String content,
	LocalDateTime createdAt
) {
}
