/**
	@Authored By Ajith Thazath 
	Created for Demo project
**/
package com.training.MyShoppingApp.ErrorHandler;

import java.net.http.HttpHeaders;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.training.MyShoppingApp.Error.ErrorDetails;
import com.training.MyShoppingApp.ExceptionHandler.FileNotFoundException;
import com.training.MyShoppingApp.ExceptionHandler.FileTypeNotSupportedException;
import com.training.MyShoppingApp.ExceptionHandler.InvalidQueryParameterException;
import com.training.MyShoppingApp.ExceptionHandler.NotHavePermissionToPerformThisActionException;
import com.training.MyShoppingApp.ExceptionHandler.OTPValidationFailed;
import com.training.MyShoppingApp.ExceptionHandler.OrderNotFoundException;
import com.training.MyShoppingApp.ExceptionHandler.ProductNotFoundException;
import com.training.MyShoppingApp.ExceptionHandler.TokenExpiredException;
import com.training.MyShoppingApp.ExceptionHandler.UserAlreadyExistsException;
import com.training.MyShoppingApp.ExceptionHandler.UserNotFoundException;
import com.training.MyShoppingApp.constants.constants;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
@RestController
public class ErrorHandler extends ResponseEntityExceptionHandler {

	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(status.BAD_REQUEST.value(),
				constants.VALIDTION_FAILED, ex.getBindingResult().toString());
		return new ResponseEntity(errorDetails, status.BAD_REQUEST);
	}

	@ExceptionHandler(ProductNotFoundException.class)
	protected ResponseEntity<Object> handleProductNotFoundEntity(
			ProductNotFoundException ex, WebRequest request) {
		ErrorDetails notFound = new ErrorDetails(HttpStatus.BAD_REQUEST.value(),
				constants.INVALID_PRODUCT_ID, ex.toString());
		return new ResponseEntity<>(notFound, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(TokenExpiredException.class)
	public ResponseEntity<Object> tokenExpiredError(Exception ex,
			HttpServletRequest request) {
		ErrorDetails notFound = new ErrorDetails(HttpStatus.BAD_REQUEST.value(),
				constants.INVALID_TOKEN, ex.toString());
		return new ResponseEntity<>(notFound, HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(InvalidQueryParameterException.class)
	public ResponseEntity<Object> InvalidQueryParameterError(Exception ex,
			HttpServletRequest request) {
		ErrorDetails invalidQueryParam = new ErrorDetails(
				HttpStatus.BAD_REQUEST.value(), ex.getMessage(), ex.toString());
		return new ResponseEntity<>(invalidQueryParam, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(OrderNotFoundException.class)
	public ResponseEntity<Object> OrderNotFound(Exception ex,
			HttpServletRequest request) {
		ErrorDetails invalidQueryParam = new ErrorDetails(
				HttpStatus.NOT_FOUND.value(), ex.getMessage(), ex.toString());
		return new ResponseEntity<>(invalidQueryParam, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Object> UserNotFoundExceptionHandler(Exception ex,
			HttpServletRequest request) {
		ErrorDetails invalidQueryParam = new ErrorDetails(
				HttpStatus.NOT_FOUND.value(), ex.getMessage(), ex.toString());
		return new ResponseEntity<>(invalidQueryParam, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(FileNotFoundException.class)
	public ResponseEntity<Object> FileNotFoundExceptionHandler(Exception ex,
			HttpServletRequest request) {
		ErrorDetails invalidQueryParam = new ErrorDetails(
				HttpStatus.NOT_FOUND.value(), ex.getMessage(), ex.toString());
		return new ResponseEntity<>(invalidQueryParam, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public ResponseEntity<Object> MaxUploadSizeExceededExceptionHandler(
			Exception ex, HttpServletRequest request) {
		ErrorDetails invalidQueryParam = new ErrorDetails(
				HttpStatus.NOT_FOUND.value(), ex.getMessage(), ex.toString());
		return new ResponseEntity<>(invalidQueryParam, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Object> ConstraintViolationExceptionHandler(
			Exception ex, HttpServletRequest request) {
		ErrorDetails invalidQueryParam = new ErrorDetails(
				HttpStatus.BAD_REQUEST.value(), ex.getMessage(), ex.toString());
		return new ResponseEntity<>(invalidQueryParam, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(FileTypeNotSupportedException.class)
	public ResponseEntity<Object> FileTypeNotSupportedExceptionHandler(
			Exception ex, HttpServletRequest request) {
		ErrorDetails invalidQueryParam = new ErrorDetails(
				HttpStatus.BAD_REQUEST.value(), ex.getMessage(), ex.toString());
		return new ResponseEntity<>(invalidQueryParam, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<Object> badCredintialsExceptionHandler(Exception ex,
			HttpServletRequest request) {
		ErrorDetails badCrediantial = new ErrorDetails(
				HttpStatus.UNAUTHORIZED.value(), ex.getMessage(),
				ex.toString());
		return new ResponseEntity<>(badCrediantial, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(UserAlreadyExistsException.class)
	public ResponseEntity<Object> UserAlreadyExistsExceptionHandler(
			Exception ex, HttpServletRequest request) {
		ErrorDetails badCrediantial = new ErrorDetails(
				HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS.value(),
				ex.getMessage(), ex.toString());
		return new ResponseEntity<>(badCrediantial, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(NotHavePermissionToPerformThisActionException.class)
	public ResponseEntity<Object> NotHavePermissionToPerformThisActionExceptionHandler(
			Exception ex, HttpServletRequest request) {
		ErrorDetails res = new ErrorDetails(HttpStatus.FORBIDDEN.value(),
				ex.getMessage(), ex.toString());
		return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(OTPValidationFailed.class)
	public ResponseEntity<Object> OTPvalidationFailedHandler(Exception ex,
			HttpServletRequest request) {
		ErrorDetails res = new ErrorDetails(HttpStatus.FORBIDDEN.value(),
				ex.getMessage(), ex.toString());
		return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
	}
}
