package oz.bookiarybacked.domain.bookshelf.application;

import static oz.bookiarybacked.exception.ErrorMessages.*;

import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import oz.bookiarybacked.common.dto.Page;
import oz.bookiarybacked.common.dto.PageParam;
import oz.bookiarybacked.common.exception.PermissionDeniedException;
import oz.bookiarybacked.domain.book.domain.dto.BookSummaryDto;
import oz.bookiarybacked.domain.bookshelf.domain.repository.UserBookQueryRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BookshelfService {
	private final UserBookQueryRepository userBookQueryRepository;

	public Page<BookSummaryDto> retrieveBooks(Long loginId, Long userId, PageParam pageParam) {
		checkPermission(loginId, userId);
		return userBookQueryRepository.retrieveBookshelf(userId, pageParam);
	}

	private void checkPermission(Long loginId, Long id) {
		// 책장 조회는 본인만 가능
		if (!Objects.equals(loginId, id)) {
			throw new PermissionDeniedException(PERMISSION_DENIED);
		}
	}
}
