package oz.bookiarybacked.application.auth.dto;

public record TokenPair(
	String accessToken,
	String refreshToken
) {

}
