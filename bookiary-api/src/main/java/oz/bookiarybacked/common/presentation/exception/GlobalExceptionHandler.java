package oz.bookiarybacked.common.presentation.exception;

import static org.springframework.http.HttpStatus.*;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oz.bookiarybacked.common.presentation.dto.ApiResult;
import oz.bookiarybacked.exception.ForbiddenException;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class GlobalExceptionHandler {

	/**
	 * 4XX 에러 처리
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiResult<Void>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		log.debug(e.getMessage(), e.fillInStackTrace());

		ApiResult<Void> result = ApiResult.error(HttpStatus.BAD_REQUEST, extractErrorMessages(e));

		return ResponseEntity.badRequest()
			.body(result);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ApiResult<Void>> handleIllegalArgumentException(IllegalArgumentException e) {
		log.debug(e.getMessage(), e.fillInStackTrace());

		ApiResult<Void> result = ApiResult.error(BAD_REQUEST, e.getMessage());

		return ResponseEntity.badRequest()
			.body(result);
	}

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ApiResult<Void>> handleEntityNotFoundException(EntityNotFoundException e) {
		log.debug(e.getMessage(), e.fillInStackTrace());

		ApiResult<Void> result = ApiResult.error(NOT_FOUND, e.getMessage());

		return ResponseEntity
			.status(NOT_FOUND)
			.body(result);
	}

	@ExceptionHandler(ForbiddenException.class)
	public ResponseEntity<ApiResult<Void>> handleForbiddenException(ForbiddenException e) {
		log.debug(e.getMessage(), e.fillInStackTrace());

		ApiResult<Void> result = ApiResult.error(FORBIDDEN, e.getMessage());

		return ResponseEntity
			.status(FORBIDDEN)
			.body(result);
	}

	@ExceptionHandler(EntityExistsException.class)
	public ResponseEntity<ApiResult<Void>> handleEntityExistsException(EntityExistsException e) {
		log.debug(e.getMessage(), e.fillInStackTrace());

		ApiResult<Void> result = ApiResult.error(CONFLICT, e.getMessage());

		return ResponseEntity
			.status(CONFLICT)
			.body(result);
	}

	/**
	 * 5XX 에러 처리
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResult<Void>> handleException(Exception e) {
		log.error(e.getMessage(), e.fillInStackTrace());

		ApiResult<Void> result = ApiResult.error(INTERNAL_SERVER_ERROR, e.getMessage());

		return ResponseEntity
			.status(INTERNAL_SERVER_ERROR)
			.body(result);
	}

	private String extractErrorMessages(MethodArgumentNotValidException e) {
		return e.getBindingResult()
			.getAllErrors()
			.stream()
			.map(DefaultMessageSourceResolvable::getDefaultMessage)
			.toList()
			.toString();
	}
}

