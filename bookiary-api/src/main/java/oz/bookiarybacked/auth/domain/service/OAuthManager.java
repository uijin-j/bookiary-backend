package oz.bookiarybacked.auth.domain.service;

import java.net.URI;

import oz.bookiarybacked.auth.domain.dto.OAuthUser;
import oz.bookiarybacked.auth.domain.model.AuthProviderType;

public interface OAuthManager {
	URI getLoginPageUrl();

	OAuthUser retrieveOAuthUser(String code);

	AuthProviderType getType();
}
