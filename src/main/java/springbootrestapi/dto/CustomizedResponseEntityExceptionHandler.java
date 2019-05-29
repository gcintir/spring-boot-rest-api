package springbootrestapi.dto;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import springbootrestapi.dto.ResponseType.Response;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return new ResponseEntity<>(new ApiResponseDto(Response.INVALID_PARAMETERS), HttpStatus.BAD_REQUEST);
	}

	protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status,
			WebRequest request) {
		return new ResponseEntity<>(new ApiResponseDto(Response.INVALID_PARAMETERS), HttpStatus.BAD_REQUEST);
	}

	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		return new ResponseEntity<>(new ApiResponseDto(Response.INVALID_PARAMETERS), HttpStatus.BAD_REQUEST);
	}

	protected ResponseEntity<Object> handleMissingServletRequestPart(MissingServletRequestPartException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return new ResponseEntity<>(new ApiResponseDto(Response.INVALID_PARAMETERS), HttpStatus.BAD_REQUEST);
	}

	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return new ResponseEntity<>(new ApiResponseDto(Response.INVALID_PARAMETERS), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({ MethodArgumentTypeMismatchException.class })
	protected ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return new ResponseEntity<>(new ApiResponseDto(Response.INVALID_PARAMETERS), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({ ConstraintViolationException.class })
	protected ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		return new ResponseEntity<>(new ApiResponseDto(Response.INVALID_PARAMETERS), HttpStatus.BAD_REQUEST);
	}

	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		return new ResponseEntity<>(new ApiResponseDto(Response.NOT_FOUND), HttpStatus.NOT_FOUND);
	}

	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return new ResponseEntity<>(new ApiResponseDto(Response.BAD_REQUEST), HttpStatus.METHOD_NOT_ALLOWED);
	}

	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return new ResponseEntity<>(new ApiResponseDto(Response.BAD_REQUEST), HttpStatus.UNSUPPORTED_MEDIA_TYPE);
	}

	@ExceptionHandler({ RecordNotfoundException.class })
	protected ResponseEntity<Object> handleRecordNotfoundException(RecordNotfoundException ex, WebRequest request) {
		return new ResponseEntity<>(new ApiResponseDto(Response.NOT_FOUND), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({ Exception.class })
	protected ResponseEntity<Object> handleAll(Exception ex, HttpHeaders headers, HttpStatus status,
			WebRequest request) {
		return new ResponseEntity<>(new ApiResponseDto(Response.INTERNAL_SERVER_ERROR),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
