package oz.bookiarybacked.application.book;

import oz.bookiarybacked.application.book.dto.BookDto;
import oz.bookiarybacked.application.book.dto.request.BookSearchParam;
import oz.bookiarybacked.application.common.dto.Page;

public interface BookSearchService {
	Page<BookDto> search(BookSearchParam searchParam);
}
