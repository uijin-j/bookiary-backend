package oz.bookiarybacked.domain.user.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import oz.bookiarybacked.domain.user.model.User;
import oz.bookiarybacked.domain.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserDomainService {
	private final UserRepository userRepository;

	@Transactional
	public User signup() {
		return userRepository.save(User.signUp());
	}
}
