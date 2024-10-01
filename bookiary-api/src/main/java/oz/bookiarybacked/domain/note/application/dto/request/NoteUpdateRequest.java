package oz.bookiarybacked.domain.note.application.dto.request;

import static oz.bookiarybacked.common.exception.ErrorMessages.*;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "노트 수정 요청")
public record NoteUpdateRequest(
	@NotBlank(message = CONTENT_REQUIRED)
	@Schema(description = "내용", example = "이 책은 so so 했다.")
	String content
) {
}
