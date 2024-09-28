package oz.bookiarybacked.auth.domain.model;

public enum AuthProviderType {
	KAKAO;

	public static AuthProviderType from(String provider) {
		return AuthProviderType.valueOf(provider.toUpperCase());
	}
}
