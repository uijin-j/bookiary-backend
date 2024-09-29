package oz.bookiarybacked.domain.note.application.dto.request;

import static oz.bookiarybacked.common.exception.ErrorMessages.*;

import jakarta.validation.constraints.NotBlank;

public record NoteUpdateRequest(
	@NotBlank(message = CONTENT_REQUIRED)
	String content
) {
}
