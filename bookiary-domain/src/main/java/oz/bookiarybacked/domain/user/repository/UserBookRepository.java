package oz.bookiarybacked.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import oz.bookiarybacked.domain.user.model.UserBook;

public interface UserBookRepository extends JpaRepository<UserBook, Long> {
}
