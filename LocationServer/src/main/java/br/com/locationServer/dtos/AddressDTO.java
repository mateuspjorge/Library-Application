package br.com.locationServer.dtos;

import java.io.Serializable;

import br.com.locationServer.entitys.Address;
import br.com.locationServer.exception.CityException;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressDTO implements Serializable {

	private static final long serialVersionUID = -6605389815827601688L;

	private Long id;

	private Long cityId;

	private Long newCityId;

	private Integer number;

	private Integer newNumber;

	private String cep;

	private String newCep;

	private String street;

	private String newStreet;

	private String district;

	private String newDistrict;

	private String complement;

	private String newComplement;

	private CityDTO city;

	public static AddressDTO convertAddressToDto(Address address) throws CityException {
		return AddressDTO.builder()
						 .id(address.getId())
						 .cityId(address.getCityId())
						 .number(address.getNumber())
						 .cep(address.getCep())
						 .street(address.getStreet())
						 .district(address.getDistrict())
						 .complement(address.getComplement())
						 .build();
	}

}