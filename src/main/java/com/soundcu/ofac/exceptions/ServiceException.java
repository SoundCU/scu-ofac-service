package com.soundcu.ofac.exceptions;

import org.springframework.http.HttpStatus;

public class ServiceException extends RuntimeException
{
	private static final long serialVersionUID = 147660967531228859L;

	private int code;
	private HttpStatus status;
	
	public ServiceException(int code, HttpStatus status, String message)
	{
		super(message);
		this.code = code;
		this.status = status;
	}
	
	public int getCode()
	{
		return code;
	}
	
	public HttpStatus getStatus()
	{
		return status;
	}
}
