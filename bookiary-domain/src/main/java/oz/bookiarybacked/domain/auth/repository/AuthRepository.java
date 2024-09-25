package oz.bookiarybacked.domain.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import oz.bookiarybacked.domain.auth.model.AuthProvider;
import oz.bookiarybacked.domain.auth.model.AuthProviderType;
import oz.bookiarybacked.domain.user.model.User;

public interface AuthRepository extends JpaRepository<AuthProvider, Long> {
	boolean existsByTypeAndProviderId(AuthProviderType type, String providerId);

	@Query("""
		SELECT u 
		FROM User u 
		JOIN AuthProvider o ON o.type = :type AND o.providerId = :providerId AND o.userId = u.id
		""")
	Optional<User> findUserByTypeAndProviderId(AuthProviderType type, String providerId);
}
