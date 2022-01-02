package br.com.userServer.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentException extends Exception {

	private static final long serialVersionUID = -7813141197521619284L;

	private final String message;

	public StudentException(String message) {
		super(message);
		this.message = message;
	}

}