package oz.bookiarybacked.domain.user.domain.service;

import static oz.bookiarybacked.common.exception.ErrorMessages.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import oz.bookiarybacked.domain.user.domain.model.User;
import oz.bookiarybacked.domain.user.domain.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserDomainService {
	private final UserRepository userRepository;

	public User getById(Long userId) {
		return userRepository.findById(userId)
			.orElseThrow(() -> new EntityNotFoundException(USER_NOT_FOUND));
	}
}
