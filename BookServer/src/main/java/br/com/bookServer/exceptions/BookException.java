package br.com.bookServer.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookException extends Exception {

	private static final long serialVersionUID = 1409450145106283276L;

	private final String message;

	public BookException(String message) {
		super(message);
		this.message = message;
	}

}