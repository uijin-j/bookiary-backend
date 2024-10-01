package oz.bookiarybacked.domain.bookshelf.presentation.api;

import static oz.bookiarybacked.domain.bookshelf.presentation.api.BookshelfResponseExamples.*;

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
import oz.bookiarybacked.common.dto.PageParam;
import oz.bookiarybacked.common.presentation.dto.ApiResult;
import oz.bookiarybacked.domain.bookshelf.domain.dto.BookSummaryDto;
import oz.bookiarybacked.domain.bookshelf.domain.dto.response.RetrieveBookRes;

@Tag(name = "Bookshelf", description = "책장(사용자 도서) 관련 API")
public interface BookshelfApiDoc {
	@Operation(summary = "책장 조회 API", security = @SecurityRequirement(name = "토큰 기반 인증"))
	@ApiResponse(responseCode = "200", description = "OK", content = {
		@Content(schema = @Schema(implementation = ApiResult.class, oneOf = {Page.class, BookSummaryDto.class}),
			examples = {@ExampleObject(name = "200", value = RETRIEVE_BOOKSHELF_200_RESPONSE)}
		)
	})
	@Parameters({
		@Parameter(name = "userId",
			description = "조회할 책장의 사용자 식별자 (현재는 본인만 가능)",
			in = ParameterIn.PATH,
			required = true
		),
		@Parameter(
			name = "start",
			description = "시작 페이지",
			in = ParameterIn.QUERY,
			schema = @Schema(type = "integer", defaultValue = "0")
		),
		@Parameter(
			name = "size",
			description = "페이지 크기",
			in = ParameterIn.QUERY,
			schema = @Schema(type = "integer", defaultValue = "10")
		)
	})
	ResponseEntity<ApiResult<Page<BookSummaryDto>>> retrieveBookshelf(Long loginId, Long userId,
		@Parameter(hidden = true) PageParam pageParam);

	@Operation(summary = "책 조회 API", description = "소유자가 작성한 노트도 함께 조회됩니다.",
		security = @SecurityRequirement(name = "토큰 기반 인증"))
	@ApiResponse(responseCode = "200", description = "OK", content = {
		@Content(schema = @Schema(implementation = ApiResult.class, oneOf = {RetrieveBookRes.class}),
			examples = {@ExampleObject(name = "200", value = RETRIEVE_BOOK_200_RESPONSE)}
		)
	})
	@Parameter(
		name = "bookId",
		description = "조회할 책(user_book)의 식별자",
		in = ParameterIn.PATH,
		required = true
	)
	ResponseEntity<ApiResult<RetrieveBookRes>> retrieveBook(Long loginId, Long bookId);

	@Operation(summary = "책장에서 책 꺼내기 API (hard delete)", description = "책장에서 책을 꺼내고 완전히 삭제합니다.",
		security = @SecurityRequirement(name = "토큰 기반 인증"))
	@ApiResponse(responseCode = "204", description = "NO_CONTENT", content = {
		@Content(schema = @Schema(implementation = ApiResult.class),
			examples = {@ExampleObject(name = "200", value = TAKE_BOOK_OFF_204_RESPONSE)}
		)
	})
	@Parameter(
		name = "bookId",
		description = "책장에서 꺼낼 책(user_book)의 식별자",
		in = ParameterIn.PATH,
		required = true)
	ResponseEntity<ApiResult<Void>> takeBookOff(Long loginId, Long bookId);
}
