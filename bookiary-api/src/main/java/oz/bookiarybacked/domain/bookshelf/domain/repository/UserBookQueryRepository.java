package oz.bookiarybacked.domain.bookshelf.domain.repository;

import oz.bookiarybacked.common.dto.Page;
import oz.bookiarybacked.common.dto.PageParam;
import oz.bookiarybacked.domain.bookshelf.domain.dto.BookSummaryDto;
import oz.bookiarybacked.domain.bookshelf.domain.dto.response.RetrieveBookRes;

public interface UserBookQueryRepository {
	Page<BookSummaryDto> retrieveBookshelf(Long userId, PageParam pageParam);

	RetrieveBookRes retrieveBook(Long userBookId);
}
