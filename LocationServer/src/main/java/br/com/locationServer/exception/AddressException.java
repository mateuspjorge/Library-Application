package br.com.locationServer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AddressException extends Exception {

	private static final long serialVersionUID = -56512500263644866L;

	private String message;

	public AddressException(String message) {
		super(message);
		this.message = message;
	}

}