package oz.bookiarybacked.domain.auth.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import oz.bookiarybacked.domain.auth.dto.OAuthUser;
import oz.bookiarybacked.domain.auth.model.AuthProvider;
import oz.bookiarybacked.domain.auth.repository.AuthRepository;
import oz.bookiarybacked.domain.user.model.User;
import oz.bookiarybacked.domain.user.repository.UserRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthDomainService {

	private final AuthRepository authRepository;
	private final UserRepository userRepository;

	@Transactional
	public User getUser(OAuthUser oAuthUser) {
		return authRepository.findUserByTypeAndProviderId(oAuthUser.providerType(), oAuthUser.providerId())
			.orElseGet(() -> signup(oAuthUser));
	}

	private User signup(OAuthUser oAuthUser) {
		User user = userRepository.save(User.signUp());
		AuthProvider authProvider = AuthProvider.of(oAuthUser, user);
		authRepository.save(authProvider);
		return user;
	}
}
