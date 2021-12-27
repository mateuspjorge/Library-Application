package br.com.locationServer.dtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

import br.com.locationServer.entitys.Address;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressDTO implements Serializable {

	private static final long serialVersionUID = -6605389815827601688L;

	private Long id;

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

	private String messageError;

	private CityDTO city;

	private CityDTO newCity;

	public static AddressDTO createDtoWithMessageError(String error) {
		return AddressDTO.builder().messageError(error).build();
	}

	public static AddressDTO convertAddressToDto(Address address) {
		return AddressDTO.builder()
						 .id(address.getId())
						 .number(address.getNumber())
						 .cep(address.getCep())
						 .street(address.getStreet())
						 .district(address.getDistrict())
						 .complement(address.getComplement())
						 .city(CityDTO.convertCityToDto(address.getCity()))
						 .build();
	}

	public static Address convertDtoToAddress(AddressDTO addressDto) {
		return Address.builder()
					  .id(addressDto.getId())
					  .number(addressDto.getNumber())
					  .cep(addressDto.getCep())
					  .street(addressDto.getStreet())
					  .district(addressDto.getDistrict())
					  .complement(addressDto.getComplement())
					  .city(CityDTO.convertDtoToCity(addressDto.getCity()))
					  .build();
	}

	public static List<AddressDTO> convertListAddressToListDto(List<Address> listAddress) {
		List<AddressDTO> addressDTOs = new ArrayList<>();
		if (!CollectionUtils.isEmpty(listAddress)) {
			listAddress.forEach(address -> addressDTOs.add(convertAddressToDto(address)));
		}
		return addressDTOs;
	}

	public static List<Address> convertListDtoToListAddress(List<AddressDTO> addressesDto) {
		List<Address> addresses = new ArrayList<>();
		if (!CollectionUtils.isEmpty(addressesDto)) {
			addressesDto.forEach(dto -> addresses.add(convertDtoToAddress(dto)));
		}
		return addresses;
	}

}