package oz.bookiarybacked.domain.book.presentation.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import oz.bookiarybacked.common.dto.Page;
import oz.bookiarybacked.common.presentation.annotation.Login;
import oz.bookiarybacked.common.presentation.dto.ApiResult;
import oz.bookiarybacked.domain.book.application.BookSearchService;
import oz.bookiarybacked.domain.book.application.BookService;
import oz.bookiarybacked.domain.book.application.dto.request.BookSearchParam;
import oz.bookiarybacked.domain.book.domain.dto.BookDto;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookApi {
	private final BookSearchService bookSearchService;
	private final BookService bookService;

	/**
	 * 책 검색 API
	 * @param searchParam 검색 파라미터 (키워드, 시작 페이지, 페이지 크기)
	 * @return Page<BookDto> 검색 결과
	 */
	@GetMapping
	public ResponseEntity<ApiResult<Page<BookDto>>> search(
		BookSearchParam searchParam
	) {
		Page<BookDto> page = bookSearchService.search(searchParam);
		ApiResult<Page<BookDto>> result = ApiResult.ok(page);

		return ResponseEntity
			.status(HttpStatus.OK)
			.body(result);
	}

	/**
	 * 책 저장 API
	 * @param userId 요청한 사용자 식별자
	 * @param isbn 저장할 책의 ISBN
	 */
	@PostMapping("/{isbn}/save")
	public ResponseEntity<ApiResult<Void>> addBookToShelf(
		@Login Long userId,
		@PathVariable String isbn
	) {
		bookService.addBookToShelf(userId, isbn);
		ApiResult<Void> result = ApiResult.of(
			HttpStatus.CREATED);

		return ResponseEntity
			.status(HttpStatus.CREATED)
			.body(result);
	}
}
