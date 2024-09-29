package oz.bookiarybacked.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("book.naver")
public record NaverSearchProperties(
	String clientId,
	String clientSecret,
	String searchRequestUri,
	String searchDetailRequestUri
) {
}
