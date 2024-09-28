package oz.bookiarybacked.domain.user.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import oz.bookiarybacked.domain.user.domain.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
