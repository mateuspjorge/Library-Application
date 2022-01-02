package br.com.locationServer.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CountryException extends Exception {

	private static final long serialVersionUID = -5157998095447871396L;

	private String message;

	public CountryException(String message) {
		super(message);
		this.message = message;
	}

}