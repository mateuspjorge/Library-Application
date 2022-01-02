package br.com.userServer.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserException extends Exception {

	private static final long serialVersionUID = -5630299948191600725L;

	private final String message;

	public UserException(String message) {
		super(message);
		this.message = message;
	}

}