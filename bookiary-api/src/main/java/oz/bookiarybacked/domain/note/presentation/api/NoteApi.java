package oz.bookiarybacked.domain.note.presentation.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import oz.bookiarybacked.common.presentation.annotation.Login;
import oz.bookiarybacked.common.presentation.dto.ApiResult;
import oz.bookiarybacked.domain.note.application.NoteService;
import oz.bookiarybacked.domain.note.application.dto.request.NoteCreateRequest;

@RestController
@RequestMapping("/api/notes")
@RequiredArgsConstructor
public class NoteApi {
	private final NoteService noteService;

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
}
