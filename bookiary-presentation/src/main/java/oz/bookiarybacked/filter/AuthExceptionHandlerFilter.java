package oz.bookiarybacked.filter;

import static java.nio.charset.StandardCharsets.*;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.util.MimeTypeUtils.*;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import oz.bookiarybacked.presentation.api.ApiResult;

@Slf4j
@Component
public class AuthExceptionHandlerFilter extends OncePerRequestFilter {

	private static final ObjectMapper objectMapper = new ObjectMapper();

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
		FilterChain filterChain) throws ServletException, IOException {
		try {
			filterChain.doFilter(request, response);
		} catch (AuthenticationException e) {
			log.debug(e.getMessage(), e.fillInStackTrace());

			response.setContentType(APPLICATION_JSON_VALUE);
			response.setCharacterEncoding(UTF_8.name());
			response.setStatus(UNAUTHORIZED.value());

			ApiResult<Void> body = ApiResult.error(UNAUTHORIZED, e.getMessage());

			response.getWriter().write(objectMapper.writeValueAsString(body));
		}
	}

}
