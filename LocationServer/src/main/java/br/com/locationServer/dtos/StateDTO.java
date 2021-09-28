package br.com.locationServer.dtos;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StateDTO implements Serializable {

	private static final long serialVersionUID = 8993945325207739029L;

	private Long id;

	private Long countryId;

	private Long newCountryId;

	private String name;

	private String newName;

	private String initials;

	private String newInitials;

}