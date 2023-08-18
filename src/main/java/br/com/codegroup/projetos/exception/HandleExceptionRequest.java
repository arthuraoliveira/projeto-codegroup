package br.com.codegroup.projetos.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.codegroup.projetos.exception.error.BadRequestApiError;

@ControllerAdvice
public class HandleExceptionRequest extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ ApiException.class })
	public ResponseEntity<BadRequestApiError> handleCustomException(ApiException exception) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		BadRequestApiError apiError = objectMapper.readValue(exception.getResponseBody(), BadRequestApiError.class);
		return ResponseEntity.status(exception.getStatusCode()).body(apiError);
	}
	
    @ExceptionHandler({ InvalidException.class })
    public ResponseEntity<Object> handleException(InvalidException ex) {
        BadRequestApiError apiError = new BadRequestApiError(ex.getErrors());
        return new ResponseEntity<>(apiError, new HttpHeaders(), HttpStatus.valueOf(apiError.getStatus()));
    }

}
