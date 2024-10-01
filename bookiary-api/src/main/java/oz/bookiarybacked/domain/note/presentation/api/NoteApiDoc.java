package oz.bookiarybacked.domain.note.presentation.api;

import static oz.bookiarybacked.domain.note.presentation.api.NoteResponseExamples.*;

import org.springframework.http.ResponseEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import oz.bookiarybacked.common.presentation.dto.ApiResult;
import oz.bookiarybacked.domain.note.application.dto.request.NoteCreateRequest;
import oz.bookiarybacked.domain.note.application.dto.request.NoteUpdateRequest;

@Tag(name = "Note", description = "노트 관련 API")
public interface NoteApiDoc {
	@Operation(summary = "노트 생성 API", security = @SecurityRequirement(name = "토큰 기반 인증"))
	@ApiResponse(responseCode = "201", description = "CREATED", content = {
		@Content(schema = @Schema(implementation = ApiResult.class, oneOf = {Void.class}),
			examples = {@ExampleObject(name = "201", value = NOTE_CREATE_201_RESPONSE)}
		)
	})
	ResponseEntity<ApiResult<Void>> create(Long loginId, NoteCreateRequest request);

	@Operation(summary = "노트 수정 API", security = @SecurityRequirement(name = "토큰 기반 인증"))
	@ApiResponse(responseCode = "200", description = "OK", content = {
		@Content(schema = @Schema(implementation = ApiResult.class, oneOf = {Void.class}),
			examples = {@ExampleObject(name = "200", value = NOTE_UPDATE_200_RESPONSE)}
		)
	})
	@Parameter(name = "id", description = "수정할 노트 식별자", required = true)
	ResponseEntity<ApiResult<Void>> update(Long loginId, Long id, NoteUpdateRequest request);

	@Operation(summary = "노트 삭제 API", security = @SecurityRequirement(name = "토큰 기반 인증"))
	@ApiResponse(responseCode = "204", description = "NO_CONTENT", content = {
		@Content(schema = @Schema(implementation = ApiResult.class, oneOf = {Void.class}),
			examples = {@ExampleObject(name = "204", value = NOTE_DELETE_204_RESPONSE)}
		)
	})
	@Parameter(name = "id", description = "삭제할 노트 식별자", required = true)
	ResponseEntity<ApiResult<Void>> delete(Long loginId, Long id);
}
