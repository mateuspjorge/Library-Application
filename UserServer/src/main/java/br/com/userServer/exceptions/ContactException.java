package br.com.userServer.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactException extends Exception {

	private static final long serialVersionUID = -1540025666200596429L;

	private final String message;

	public ContactException(String message) {
		super(message);
		this.message = message;
	}

}