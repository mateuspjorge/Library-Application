package br.com.modelServer.entitys;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class City {

	private Long id;

	private Long stateId;

	private String name;

}