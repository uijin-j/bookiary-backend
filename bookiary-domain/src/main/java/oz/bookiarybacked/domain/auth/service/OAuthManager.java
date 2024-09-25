package oz.bookiarybacked.domain.auth.service;

import java.net.URI;

import oz.bookiarybacked.domain.auth.dto.OAuthUser;
import oz.bookiarybacked.domain.auth.model.AuthProviderType;

public interface OAuthManager {
	URI getLoginPageUrl();

	OAuthUser retrieveOAuthUser(String code);

	AuthProviderType getType();
}
