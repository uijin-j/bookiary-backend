package oz.bookiarybacked.domain.bookshelf.application;

import static oz.bookiarybacked.exception.ErrorMessages.*;

import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import oz.bookiarybacked.common.dto.Page;
import oz.bookiarybacked.common.dto.PageParam;
import oz.bookiarybacked.common.exception.PermissionDeniedException;
import oz.bookiarybacked.domain.bookshelf.domain.dto.BookDetailDto;
import oz.bookiarybacked.domain.bookshelf.domain.dto.BookSummaryDto;
import oz.bookiarybacked.domain.bookshelf.domain.model.UserBook;
import oz.bookiarybacked.domain.bookshelf.domain.repository.UserBookQueryRepository;
import oz.bookiarybacked.domain.bookshelf.domain.repository.UserBookRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BookshelfService {
	private final UserBookQueryRepository userBookQueryRepository;
	private final UserBookRepository userBookRepository;

	public Page<BookSummaryDto> retrieveBooks(Long loginId, Long userId, PageParam pageParam) {
		checkPermission(loginId, userId);
		return userBookQueryRepository.retrieveBookshelf(userId, pageParam);
	}

	public BookDetailDto retrieveBook(Long loginId, Long userBookId) {
		UserBook book = userBookRepository.findById(userBookId)
			.orElseThrow(() -> new EntityNotFoundException(USER_BOOK_NOT_FOUND));

		book.checkReadPermission(loginId);

		return userBookQueryRepository.retrieveBook(userBookId);
	}

	@Transactional
	public void takeBookOff(Long loginId, Long bookId) {
		userBookRepository.findById(bookId)
			.ifPresent(book -> checkPermissionAndTakeOff(loginId, bookId, book));
	}

	private void checkPermission(Long loginId, Long id) {
		// 책장 조회는 본인만 가능
		if (!Objects.equals(loginId, id)) {
			throw new PermissionDeniedException(PERMISSION_DENIED);
		}
	}

	private void checkPermissionAndTakeOff(Long loginId, Long bookId, UserBook book) {
		book.checkDeletePermission(loginId);
		userBookRepository.deleteById(bookId);
	}
}
