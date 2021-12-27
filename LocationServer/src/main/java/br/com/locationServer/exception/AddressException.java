package br.com.locationServer.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressException extends Exception {

	private static final long serialVersionUID = -56512500263644866L;

	private String message;

	public AddressException(String message) {
		super(message);
		this.message = message;
	}

}