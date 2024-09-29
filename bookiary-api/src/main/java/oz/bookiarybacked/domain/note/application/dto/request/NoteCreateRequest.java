package oz.bookiarybacked.domain.note.application.dto.request;

import static oz.bookiarybacked.common.exception.ErrorMessages.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record NoteCreateRequest(
	@NotNull(message = USER_BOOK_ID_REQUIRED)
	Long userBookId,
	@NotBlank(message = CONTENT_REQUIRED)
	String content
) {
}
