package br.com.modelServer.entitys;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Address {

	private Long id;

	private Long cityId;

	private Integer number;

	private String cep;

	private String street;

	private String district;

	private String complement;

}