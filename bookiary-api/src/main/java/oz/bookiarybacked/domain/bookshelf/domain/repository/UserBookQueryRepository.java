package oz.bookiarybacked.domain.bookshelf.domain.repository;

import oz.bookiarybacked.common.dto.Page;
import oz.bookiarybacked.common.dto.PageParam;
import oz.bookiarybacked.domain.book.domain.dto.BookSummaryDto;

public interface UserBookQueryRepository {
	Page<BookSummaryDto> retrieveBookshelf(Long userId, PageParam pageParam);
}
