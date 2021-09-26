package br.com.locationServer.dtos;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CountryDTO implements Serializable {

	private static final long serialVersionUID = 2254835245223528596L;

	private Long id;

	private String name;

	private String newName;

}