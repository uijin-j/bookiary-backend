package oz.bookiarybacked.domain.book.application;

import static oz.bookiarybacked.common.exception.ErrorMessages.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import oz.bookiarybacked.domain.book.domain.dto.BookDto;
import oz.bookiarybacked.domain.book.domain.model.Book;
import oz.bookiarybacked.domain.book.domain.repository.BookRepository;
import oz.bookiarybacked.domain.bookshelf.domain.model.UserBook;
import oz.bookiarybacked.domain.bookshelf.domain.repository.UserBookRepository;
import oz.bookiarybacked.domain.user.domain.model.User;
import oz.bookiarybacked.domain.user.domain.repository.UserRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BookService {
	private final BookRepository bookRepository;
	private final BookSearchService bookSearchService;
	private final UserRepository userRepository;
	private final UserBookRepository userBookRepository;

	@Transactional
	public void addBookToShelf(Long userId, String isbn) {
		// Step 1. 책 정보 조회
		Book book = bookRepository.findByIsbn(isbn).orElseGet(
			() -> {
				BookDto bookInfo = bookSearchService.search(isbn);
				Book newBook = Book.from(bookInfo);
				return bookRepository.save(newBook);
			}
		);

		// Step 2. 로그인 사용자 조회
		User user = userRepository.findById(userId)
			.orElseThrow(() -> new EntityNotFoundException(USER_NOT_FOUND));

		// Step 3. 책이 이미 책장에 있는지 검사
		if (userBookRepository.existsByUserIdAndBookId(userId, book.getId())) {
			throw new EntityExistsException(USER_BOOK_ALREADY_EXISTS);
		}

		// Step 4. 책을 책장에 추가
		UserBook userBook = user.addBook(book);
		userBookRepository.save(userBook);
	}
}
