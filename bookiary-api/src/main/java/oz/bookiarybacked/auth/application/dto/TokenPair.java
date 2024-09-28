package oz.bookiarybacked.auth.application.dto;

public record TokenPair(
	String accessToken,
	String refreshToken
) {

}
