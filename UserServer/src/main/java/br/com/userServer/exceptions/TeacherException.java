package br.com.userServer.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherException extends Exception {

	private static final long serialVersionUID = 4615994522998957669L;

	private final String message;

	public TeacherException(String message) {
		super(message);
		this.message = message;
	}

}