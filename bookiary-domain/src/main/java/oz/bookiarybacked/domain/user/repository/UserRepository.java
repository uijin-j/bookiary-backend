package oz.bookiarybacked.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import oz.bookiarybacked.domain.user.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
