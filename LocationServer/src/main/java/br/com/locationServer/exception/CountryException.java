package br.com.locationServer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CountryException extends Exception {

	private static final long serialVersionUID = -5157998095447871396L;

	private String message;

	public CountryException(String message) {
		super(message);
		this.message = message;
	}

}