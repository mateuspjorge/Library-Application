package br.com.locationServer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class StateException extends Exception {

	private static final long serialVersionUID = -7873628922244173792L;

	private String message;

	public StateException(String message) {
		super(message);
		this.message = message;
	}

}