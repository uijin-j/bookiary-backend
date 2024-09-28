package oz.bookiarybacked.domain.book.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import oz.bookiarybacked.domain.book.domain.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
	Optional<Book> findByIsbn(String isbn);
}
