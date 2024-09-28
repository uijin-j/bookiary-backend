package oz.bookiarybacked.auth.domain.dto;

import oz.bookiarybacked.auth.domain.model.AuthProviderType;

public record OAuthUser(
	AuthProviderType providerType,
	String providerId
) {

	public static OAuthUser of(AuthProviderType providerType, String providerId) {
		return new OAuthUser(providerType, providerId);
	}
}
