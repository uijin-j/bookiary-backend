package oz.bookiarybacked.domain.note.application.dto.request;

import static oz.bookiarybacked.common.exception.ErrorMessages.*;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "노트 생성 요청")
public record NoteCreateRequest(
	@NotNull(message = USER_BOOK_ID_REQUIRED)
	@Schema(description = "사용자 도서 ID", example = "1")
	Long userBookId,
	@NotBlank(message = CONTENT_REQUIRED)
	@Schema(description = "내용", example = "이 책은 정말 재미있다.")
	String content
) {
}
