package oz.bookiarybacked.domain.auth.model;

public enum AuthProviderType {
	KAKAO;

	public static AuthProviderType from(String provider) {
		return AuthProviderType.valueOf(provider.toUpperCase());
	}
}
