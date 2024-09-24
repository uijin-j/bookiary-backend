package oz.bookiarybacked.domain.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import oz.bookiarybacked.domain.auth.model.RefreshToken;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
}
