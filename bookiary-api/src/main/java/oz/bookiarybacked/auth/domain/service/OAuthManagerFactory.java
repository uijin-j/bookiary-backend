package oz.bookiarybacked.auth.domain.service;

import oz.bookiarybacked.auth.domain.model.AuthProviderType;

public interface OAuthManagerFactory {
	OAuthManager getOAuthManager(AuthProviderType type);
}
