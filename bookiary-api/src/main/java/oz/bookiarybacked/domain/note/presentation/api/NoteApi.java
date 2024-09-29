package oz.bookiarybacked.domain.note.presentation.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import oz.bookiarybacked.common.presentation.annotation.Login;
import oz.bookiarybacked.common.presentation.dto.ApiResult;
import oz.bookiarybacked.domain.note.application.NoteService;
import oz.bookiarybacked.domain.note.application.dto.request.NoteCreateRequest;
import oz.bookiarybacked.domain.note.application.dto.request.NoteUpdateRequest;

@RestController
@RequestMapping("/api/notes")
@RequiredArgsConstructor
public class NoteApi {
	private final NoteService noteService;

	/**
	 * 노트 생성
	 * @param loginId
	 * @param request
	 * @return
	 */
	@PostMapping
	public ResponseEntity<ApiResult<Void>> create(
		@Login Long loginId,
		@Valid @RequestBody NoteCreateRequest request
	) {
		noteService.create(loginId, request);
		ApiResult<Void> result = ApiResult.of(HttpStatus.CREATED);

		return ResponseEntity
			.status(HttpStatus.CREATED)
			.body(result);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResult<Void>> update(
		@Login Long loginId,
		@PathVariable Long id,
		@Valid @RequestBody NoteUpdateRequest request
	) {
		noteService.update(loginId, id, request);
		ApiResult<Void> result = ApiResult.of(HttpStatus.OK);

		return ResponseEntity
			.status(HttpStatus.OK)
			.body(result);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResult<Void>> delete(
		@Login Long loginId,
		@PathVariable Long id
	) {
		noteService.delete(loginId, id);
		ApiResult<Void> result = ApiResult.of(HttpStatus.OK);

		return ResponseEntity
			.status(HttpStatus.OK)
			.body(result);
	}
}
