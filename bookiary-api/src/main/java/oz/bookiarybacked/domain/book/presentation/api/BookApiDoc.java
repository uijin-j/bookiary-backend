package oz.bookiarybacked.domain.book.presentation.api;

import static oz.bookiarybacked.domain.book.presentation.api.BookResponseExamples.*;

import org.springframework.http.ResponseEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import oz.bookiarybacked.common.dto.Page;
import oz.bookiarybacked.common.presentation.dto.ApiResult;
import oz.bookiarybacked.domain.book.application.dto.request.BookSearchParam;
import oz.bookiarybacked.domain.book.domain.dto.BookDto;

@Tag(name = "Book", description = "도서 관련 API")
public interface BookApiDoc {

	@Operation(summary = "책 검색 API", security = @SecurityRequirement(name = "토큰 기반 인증"))
	@ApiResponse(responseCode = "200", description = "OK", content = {
		@Content(schema = @Schema(implementation = ApiResult.class, oneOf = {Page.class, BookDto.class}),
			examples = {@ExampleObject(name = "200", value = BOOK_SEARCH_200_RESPONSE)}
		)
	})
	@Parameters({
		@Parameter(
			name = "keyword",
			description = "검색어 (공백불가)",
			in = ParameterIn.QUERY,
			required = true,
			schema = @Schema(type = "string", example = "Real MySQL")
		),
		@Parameter(
			name = "start",
			description = "시작 페이지",
			in = ParameterIn.QUERY,
			schema = @Schema(type = "integer", defaultValue = "1")
		),
		@Parameter(
			name = "size",
			description = "페이지 크기",
			in = ParameterIn.QUERY,
			schema = @Schema(type = "integer", defaultValue = "10")
		)
	})
	ResponseEntity<ApiResult<Page<BookDto>>> search(@Parameter(hidden = true) BookSearchParam searchParam);

	@Operation(summary = "책 저장 API", description = "로그인 사용자의 책장에 책을 저장합니다.",
		security = @SecurityRequirement(name = "토큰 기반 인증"))
	@ApiResponse(responseCode = "201", description = "CREATED", content = {
		@Content(schema = @Schema(implementation = ApiResult.class),
			examples = {@ExampleObject(name = "200", value = ADD_BOOK_TO_SHELF_201_RESPONSE)}
		)
	})
	@Parameter(
		name = "isbn",
		description = "저장할 책의 ISBN",
		in = ParameterIn.PATH,
		required = true,
		schema = @Schema(type = "string", example = "9791190382176")
	)
	ResponseEntity<ApiResult<Void>> addBookToShelf(Long userId, String isbn);
}
