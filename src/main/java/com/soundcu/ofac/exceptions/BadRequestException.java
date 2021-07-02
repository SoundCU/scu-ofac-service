package com.soundcu.ofac.exceptions;

import org.springframework.http.HttpStatus;

public class BadRequestException extends ServiceException
{
	private static final long serialVersionUID = -6326157423634833882L;

	public BadRequestException(int code, String message)
	{
		super(code, HttpStatus.BAD_REQUEST, message);
	}
}
