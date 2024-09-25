package oz.bookiarybacked.domain.auth.service;

import oz.bookiarybacked.domain.auth.model.AuthProviderType;

public interface OAuthManagerFactory {
	OAuthManager getOAuthManager(AuthProviderType type);
}
