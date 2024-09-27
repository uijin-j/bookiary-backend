package oz.bookiarybacked.presentation.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import oz.bookiarybacked.application.book.BookSearchService;
import oz.bookiarybacked.application.book.dto.BookDto;
import oz.bookiarybacked.application.book.dto.request.BookSearchParam;
import oz.bookiarybacked.application.common.dto.Page;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookApiController {
	private final BookSearchService bookSearchService;

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
}
