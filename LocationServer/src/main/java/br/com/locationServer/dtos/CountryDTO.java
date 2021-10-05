package br.com.locationServer.dtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

import br.com.locationServer.entitys.Country;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CountryDTO implements Serializable {

	private static final long serialVersionUID = 2254835245223528596L;

	private Long id;

	private String name;

	private String newName;

	public static CountryDTO convertCountryToDto(Country country) {
		return CountryDTO.builder()
						 .id(country.getId())
						 .name(country.getName())
						 .build();
	}

	public static List<CountryDTO> convertListCountriesToListDto(List<Country> countries) {
		List<CountryDTO> countryDTOs = new ArrayList<>();
		if (!CollectionUtils.isEmpty(countries)) {
			countries.forEach(country -> countryDTOs.add(CountryDTO.builder()
																   .id(country.getId())
																   .name(country.getName())
																   .build()));
		}
		return countryDTOs;
	}

}