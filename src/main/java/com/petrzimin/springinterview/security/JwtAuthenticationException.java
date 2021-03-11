package com.petrzimin.springinterview.security;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;

@Getter
public class JwtAuthenticationException extends AuthenticationException {

	private HttpStatus status;

	public JwtAuthenticationException(String msg) {
		super(msg);
	}

	public JwtAuthenticationException(String msg, HttpStatus httpStatus) {
		super(msg);
		this.status = httpStatus;
	}
}
