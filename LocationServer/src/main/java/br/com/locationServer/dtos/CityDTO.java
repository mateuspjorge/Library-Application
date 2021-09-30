package br.com.locationServer.dtos;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CityDTO implements Serializable {

	private static final long serialVersionUID = -3244738527756292908L;

	private Long stateId;

	private Long newStateId;

	private String name;

	private String newName;

}