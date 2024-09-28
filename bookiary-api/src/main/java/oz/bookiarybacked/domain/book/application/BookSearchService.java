package oz.bookiarybacked.domain.book.application;

import oz.bookiarybacked.application.book.dto.request.BookSearchParam;
import oz.bookiarybacked.common.dto.Page;
import oz.bookiarybacked.domain.book.domain.dto.BookDto;

public interface BookSearchService {
	Page<BookDto> search(BookSearchParam searchParam);

	BookDto search(String isbn);
}
