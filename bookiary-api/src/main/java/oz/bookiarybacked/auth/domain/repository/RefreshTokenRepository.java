package oz.bookiarybacked.auth.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import oz.bookiarybacked.auth.domain.model.RefreshToken;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
	RefreshToken findByUserId(Long userId);
}
