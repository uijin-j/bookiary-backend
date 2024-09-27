package oz.bookiarybacked.application.infra;

import oz.bookiarybacked.application.book.dto.request.BookSearchParam;
import oz.bookiarybacked.application.common.dto.Page;
import oz.bookiarybacked.domain.book.dto.BookDto;

public interface BookSearchService {
	Page<BookDto> search(BookSearchParam searchParam);

	BookDto search(String isbn);
}
