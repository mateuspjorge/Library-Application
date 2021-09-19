package br.com.modelServer.entitys;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class State {

	private Long id;

	private Long countryId;

	private String name;

	private String initials;

}