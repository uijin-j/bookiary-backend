package oz.bookiarybacked.domain.bookshelf.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import oz.bookiarybacked.domain.bookshelf.domain.model.UserBook;

public interface UserBookRepository extends JpaRepository<UserBook, Long> {
}
