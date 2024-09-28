package oz.bookiarybacked.auth.domain.service;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import oz.bookiarybacked.auth.config.properties.JwtProperties;
import oz.bookiarybacked.auth.exception.AuthErrorMessages;
import oz.bookiarybacked.auth.exception.TokenValidationFailException;
import oz.bookiarybacked.domain.user.domain.model.User;

@Component
public class JwtManager {
	private static final long MILLISECONDS_PER_MINUTE = 60 * 1000L;
	public static final String USER_ID_CLAIM = "user_id";
	private final String issuer;
	private final SecretKey secretKey;
	private final long expiresIn;
	private final long refreshExpiresIn;

	public JwtManager(JwtProperties jwtProperties) {
		issuer = jwtProperties.issuer();
		secretKey = Keys.hmacShaKeyFor(jwtProperties.secretKey().getBytes(StandardCharsets.UTF_8));
		expiresIn = jwtProperties.expiresIn() * MILLISECONDS_PER_MINUTE;
		refreshExpiresIn = jwtProperties.refreshExpiresIn() * MILLISECONDS_PER_MINUTE;
	}

	public String generateAccessToken(User user) {
		return generateToken(user, expiresIn);
	}

	public String generateRefreshToken(User user) {
		return generateToken(user, refreshExpiresIn);
	}

	public Long getUserId(String token) {
		return parseClaims(token).get(USER_ID_CLAIM, Long.class);
	}

	public Long getExpiredAt(String token) {
		return parseClaims(token).getExpiration().getTime();
	}

	private String generateToken(User user, long expiredAfter) {
		Instant now = Instant.now();
		Instant expiredAt = now.plusMillis(expiredAfter);

		return Jwts.builder()
			.setIssuer(issuer)
			.setIssuedAt(Date.from(now))
			.setExpiration(Date.from(expiredAt))
			.claim(USER_ID_CLAIM, user.getId())
			.signWith(secretKey)
			.compact();
	}

	private Claims parseClaims(String token) {
		try {
			return Jwts.parserBuilder()
				.setSigningKey(secretKey)
				.build()
				.parseClaimsJws(token)
				.getBody();
		} catch (ExpiredJwtException e) {
			throw new TokenValidationFailException(AuthErrorMessages.EXPIRED_TOKEN);
		} catch (Exception e) {
			throw new TokenValidationFailException(AuthErrorMessages.INVALID_TOKEN);
		}
	}
}
