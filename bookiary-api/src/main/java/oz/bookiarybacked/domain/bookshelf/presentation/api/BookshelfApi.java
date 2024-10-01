package oz.bookiarybacked.domain.bookshelf.presentation.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import oz.bookiarybacked.common.dto.Page;
import oz.bookiarybacked.common.dto.PageParam;
import oz.bookiarybacked.common.presentation.annotation.Login;
import oz.bookiarybacked.common.presentation.dto.ApiResult;
import oz.bookiarybacked.domain.bookshelf.application.BookshelfService;
import oz.bookiarybacked.domain.bookshelf.domain.dto.BookSummaryDto;
import oz.bookiarybacked.domain.bookshelf.domain.dto.response.RetrieveBookRes;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BookshelfApi implements BookshelfApiDoc {
	private final BookshelfService bookshelfService;

	/**
	 * 책장 조회 API
	 * @param loginId 로그인 사용자 식별자
	 * @param userId 조회할 책장 주인의 식별자
	 * @param pageParam 페이지 파라미터 (시작 페이지, 페이지 크기)
	 */
	@GetMapping("/users/{userId}/bookshelf")
	public ResponseEntity<ApiResult<Page<BookSummaryDto>>> retrieveBookshelf(
		@Login Long loginId,
		@PathVariable Long userId,
		PageParam pageParam
	) {
		Page<BookSummaryDto> data = bookshelfService.retrieveBooks(loginId, userId, pageParam);
		ApiResult<Page<BookSummaryDto>> result = ApiResult.ok(data);

		return ResponseEntity
			.status(HttpStatus.OK)
			.body(result);
	}

	/**
	 * 책 조회 API
	 * @param loginId 로그인 사용자 식별자
	 * @param bookId 조회할 사용자 책의 식별자
	 */
	@GetMapping("/bookshelf/{bookId}")
	public ResponseEntity<ApiResult<RetrieveBookRes>> retrieveBook(
		@Login Long loginId,
		@PathVariable Long bookId
	) {
		RetrieveBookRes data = bookshelfService.retrieveBook(loginId, bookId);
		ApiResult<RetrieveBookRes> result = ApiResult.ok(data);

		return ResponseEntity
			.status(HttpStatus.OK)
			.body(result);
	}

	/**
	 * 책장에서 책 꺼내기 API (hard delete)
	 * @param loginId 로그인 사용자 식별자
	 * @param bookId 꺼낼 책의 식별자
	 */
	@DeleteMapping("/bookshelf/{bookId}")
	public ResponseEntity<ApiResult<Void>> takeBookOff(
		@Login Long loginId,
		@PathVariable Long bookId
	) {
		bookshelfService.takeBookOff(loginId, bookId);
		ApiResult<Void> result = ApiResult.of(HttpStatus.NO_CONTENT);

		return ResponseEntity
			.status(HttpStatus.NO_CONTENT)
			.body(result);
	}
}
