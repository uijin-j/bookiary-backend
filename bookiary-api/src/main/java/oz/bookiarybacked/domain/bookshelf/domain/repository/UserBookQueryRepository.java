package oz.bookiarybacked.domain.bookshelf.domain.repository;

import oz.bookiarybacked.common.dto.Page;
import oz.bookiarybacked.common.dto.PageParam;
import oz.bookiarybacked.domain.bookshelf.domain.dto.BookDetailDto;
import oz.bookiarybacked.domain.bookshelf.domain.dto.BookSummaryDto;

public interface UserBookQueryRepository {
	Page<BookSummaryDto> retrieveBookshelf(Long userId, PageParam pageParam);

	BookDetailDto retrieveBook(Long userBookId);
}
