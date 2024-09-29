package oz.bookiarybacked.auth.infra.oauth;

import static java.util.function.UnaryOperator.*;
import static java.util.stream.Collectors.*;

import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

import oz.bookiarybacked.auth.domain.model.AuthProviderType;
import oz.bookiarybacked.auth.domain.service.OAuthManager;
import oz.bookiarybacked.auth.domain.service.OAuthManagerFactory;
import oz.bookiarybacked.auth.exception.AuthErrorMessages;

@Component
public class OAuthManagerFactoryImpl implements OAuthManagerFactory {
	private final Map<AuthProviderType, OAuthManager> typeToManager;

	public OAuthManagerFactoryImpl(Map<String, OAuthManager> managers) {
		typeToManager = managers.values()
			.stream()
			.collect(toMap(OAuthManager::getType, identity()));
	}

	@Override
	public OAuthManager getOAuthManager(AuthProviderType type) {
		return Optional.ofNullable(typeToManager.get(type))
			.orElseThrow(() -> new RuntimeException(AuthErrorMessages.UNSUPPORTED_OAUTH_TYPE));
	}
}
