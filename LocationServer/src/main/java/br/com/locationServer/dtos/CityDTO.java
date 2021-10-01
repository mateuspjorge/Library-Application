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

	private Long stateId;

	private Long newStateId;

	private String name;

	private String newName;

	public static List<CityDTO> convertListCitiesToListDto(List<City> cities) {
		List<CityDTO> cityDTOs = new ArrayList<>();
		if (!CollectionUtils.isEmpty(cities)) {
			cities.forEach(city -> cityDTOs.add(CityDTO.builder()
													   .stateId(city.getStateId())
													   .name(city.getName())
													   .build()));
		}
		return cityDTOs;
	}

}