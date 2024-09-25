package oz.bookiarybacked.domain.auth.dto;

import oz.bookiarybacked.domain.auth.model.AuthProviderType;

public record OAuthUser(
	AuthProviderType providerType,
	String providerId
) {

	public static OAuthUser of(AuthProviderType providerType, String providerId) {
		return new OAuthUser(providerType, providerId);
	}
}
