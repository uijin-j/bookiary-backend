package oz.bookiarybacked.infra.auth.oauth;

import static java.util.function.UnaryOperator.*;
import static java.util.stream.Collectors.*;
import static oz.bookiarybacked.exception.ErrorMessages.*;

import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

import oz.bookiarybacked.domain.auth.model.AuthProviderType;
import oz.bookiarybacked.domain.auth.service.OAuthManager;
import oz.bookiarybacked.domain.auth.service.OAuthManagerFactory;

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
			.orElseThrow(() -> new RuntimeException(UNSUPPORTED_OAUTH_TYPE));
	}
}
