package oz.bookiarybacked.auth.domain.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import oz.bookiarybacked.auth.exception.AuthErrorMessages;
import oz.bookiarybacked.auth.exception.TokenValidationFailException;

@Entity
@Table(name = "refresh_token")
@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RefreshToken {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "user_id", nullable = false)
	private Long userId;

	@Column(name = "token", nullable = false)
	private String token;

	@Column(name = "expire_at", nullable = false)
	private LocalDateTime expireAt;

	public static RefreshToken of(Long userId, String token, LocalDateTime expireAt) {
		return RefreshToken.builder()
			.userId(userId)
			.token(token)
			.expireAt(expireAt)
			.build();
	}

	public void validate(String refreshToken) {
		if (!token.equals(refreshToken)) {
			throw new TokenValidationFailException(AuthErrorMessages.INVALID_TOKEN);
		}
	}

	public boolean isExpired() {
		return expireAt.isBefore(LocalDateTime.now());
	}
}
