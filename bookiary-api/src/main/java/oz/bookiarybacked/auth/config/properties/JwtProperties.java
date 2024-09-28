package oz.bookiarybacked.auth.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("jwt")
public record JwtProperties(
	String issuer,
	String secretKey,
	int expiresIn,
	int refreshExpiresIn
) {

}
