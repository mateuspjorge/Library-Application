package br.com.locationServer.dtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

import br.com.locationServer.entitys.City;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CityDTO implements Serializable {

	private static final long serialVersionUID = -3244738527756292908L;

	private Long id;

	private String name;

	private String newName;

	private String messageError;

	private StateDTO state;

	private StateDTO newState;

	private List<AddressDTO> addresses;

	public static CityDTO createDtoWithMessageError(String error) {
		return CityDTO.builder().messageError(error).build();
	}

	public static CityDTO convertCityToDto(City city) {
		return CityDTO.builder()
					  .id(city.getId())
					  .name(city.getName())
				      .state(StateDTO.convertStateToDto(city.getState()))
				      .addresses(AddressDTO.convertListAddressToListDto(city.getAddresses()))
				      .build();
	}
	
	public static City convertDtoToCity(CityDTO cityDto) {
		return City.builder()
				   .id(cityDto.getId())
				   .name(cityDto.getName())
				   .state(StateDTO.convertDtoToState(cityDto.getState()))
				   .addresses(AddressDTO.convertListDtoToListAddress(cityDto.getAddresses()))
				   .build();
	}

	public static List<CityDTO> convertListCitiesToListDto(List<City> cities) {
		List<CityDTO> cityDTOs = new ArrayList<>();
		if (!CollectionUtils.isEmpty(cities)) {
			cities.forEach(city -> cityDTOs.add(convertCityToDto(city)));
		}
		return cityDTOs;
	}

	public static List<City> convertListDtoToListCities(List<CityDTO> citiesDto) {
		List<City> cities = new ArrayList<>();
		if (!CollectionUtils.isEmpty(citiesDto)) {
			citiesDto.forEach(dto -> cities.add(convertDtoToCity(dto)));
		}
		return cities;
	}

}