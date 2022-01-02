package br.com.locationServer.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StateException extends Exception {

	private static final long serialVersionUID = -7873628922244173792L;

	private String message;

	public StateException(String message) {
		super(message);
		this.message = message;
	}

}