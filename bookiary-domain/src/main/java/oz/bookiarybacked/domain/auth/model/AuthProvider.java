package oz.bookiarybacked.domain.auth.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import oz.bookiarybacked.domain.auth.dto.OAuthUser;
import oz.bookiarybacked.domain.user.model.User;

@Entity
@Table(name = "auth_provider")
@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AuthProvider {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "user_id", nullable = false)
	private Long userId;

	@Column(name = "type", nullable = false)
	@Enumerated(EnumType.STRING)
	private AuthProviderType type;

	@Column(name = "provider_id", nullable = false)
	private String providerId;

	public static AuthProvider of(OAuthUser oAuthUser, User user) {
		return AuthProvider.builder()
			.userId(user.getId())
			.type(oAuthUser.providerType())
			.providerId(oAuthUser.providerId())
			.build();
	}
}
