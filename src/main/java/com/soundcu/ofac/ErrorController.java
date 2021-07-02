package com.soundcu.ofac;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.soundcu.ofac.exceptions.ServiceException;
import com.soundcu.ofac.model.ServiceError;

@ControllerAdvice
public class ErrorController extends ResponseEntityExceptionHandler
{
	private ResponseEntity<Object> handleError(int code, HttpStatus status, String message)
	{
		ServiceError error = new ServiceError(code, status, message);
		return new ResponseEntity<Object>(error, status);
	}
	
	/**
	 * Catches unsupported media type errors.
	 */
	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request)
	{
		return handleError(141, HttpStatus.UNSUPPORTED_MEDIA_TYPE, ex.getMessage());
	}
	
	/**
	 * Catches unsupported method errors.
	 */
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
			HttpRequestMethodNotSupportedException e,
			HttpHeaders headers,
			HttpStatus status,
			WebRequest request)
	{
		ServletWebRequest servletRequest = (ServletWebRequest) request;
		String message = "Cannot " + servletRequest.getRequest().getMethod();
		message += ":" + servletRequest.getRequest().getRequestURI() + ".";
		return handleError(112, status, message);
	}
	
	/**
	 * Catches invalid header type errors.
	 */
	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request)
	{
		return handleError(132, HttpStatus.BAD_REQUEST, ex.getMessage());
	}
	
	/**
	 * Catches errors specifically thrown by the OFAC service to indicate an error
	 * @param e
	 * @return ResponseEntity<Object>
	 */
	@ExceptionHandler(ServiceException.class)
	public ResponseEntity<Object> handleServiceException(ServiceException e)
	{
		return handleError(e.getCode(), e.getStatus(), e.getMessage());
	}
	
	/**
	 * Catches missing required header errors.
	 */
	@Override
	protected ResponseEntity<Object> handleServletRequestBindingException(
			final ServletRequestBindingException ex,
	        final HttpHeaders headers,
	        final HttpStatus status,
	        final WebRequest request
	    )
	{
		return handleError(132, HttpStatus.BAD_REQUEST, ex.getMessage());
	}

	/**
	 * Catches any unexpected errors.
	 * @param e
	 * @return ResponseEntity<Object>
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleUnknownException(Exception e)
	{
		e.printStackTrace();
		return handleError(500, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
	}

}
