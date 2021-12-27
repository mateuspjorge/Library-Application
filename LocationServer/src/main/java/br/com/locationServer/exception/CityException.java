package br.com.locationServer.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityException extends Exception {

	private static final long serialVersionUID = 5277617970688427639L;

	private String message;

	public CityException(String message) {
		super(message);
		this.message = message;
	}

}