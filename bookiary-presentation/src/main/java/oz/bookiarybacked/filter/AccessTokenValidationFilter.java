package oz.bookiarybacked.filter;

import static oz.bookiarybacked.exception.ErrorMessages.*;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import oz.bookiarybacked.domain.auth.service.JwtManager;
import oz.bookiarybacked.exception.TokenValidationFailException;

@Component
@RequiredArgsConstructor
public class AccessTokenValidationFilter extends OncePerRequestFilter {

	public static final String JWT_HEADER = "Authorization";
	public static final String JWT_PREFIX = "Bearer ";

	private final JwtManager jwtManager;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
		FilterChain filterChain) throws ServletException, IOException {
		String bearerToken = request.getHeader(JWT_HEADER);
		String accessToken = resolveToken(bearerToken);

		if (null != accessToken) {
			try {
				Long userId = jwtManager.getUserId(accessToken);
				Authentication auth = new UsernamePasswordAuthenticationToken(userId, null);
				SecurityContextHolder.getContext().setAuthentication(auth);
			} catch (Exception e) {
				throw new TokenValidationFailException(INVALID_TOKEN);
			}
		}

		filterChain.doFilter(request, response);
	}

	private String resolveToken(String bearerToken) {
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(JWT_PREFIX)) {
			return bearerToken.substring(JWT_PREFIX.length());
		}

		return null;
	}

}

