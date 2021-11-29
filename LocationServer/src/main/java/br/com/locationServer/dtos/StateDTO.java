package br.com.locationServer.dtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

import br.com.locationServer.entitys.State;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StateDTO implements Serializable {

	private static final long serialVersionUID = 8993945325207739029L;

	private Long id;

	private String name;

	private String newName;

	private String initials;

	private String newInitials;

	private CountryDTO country;

	private CountryDTO newCountry;

	private List<CityDTO> cities;

	public static StateDTO convertStateToDto(State state) {
		return StateDTO.builder()
				       .id(state.getId())
				       .name(state.getName())
				       .initials(state.getInitials())
				       .country(CountryDTO.convertCountryToDto(state.getCountry()))
				       .cities(CityDTO.convertListCitiesToListDto(state.getCities()))
				       .build();
	}

	public static State convertDtoToState(StateDTO stateDto) {
		return State.builder()
			        .id(stateDto.getId())
			        .name(stateDto.getName())
			        .initials(stateDto.getInitials())
			        .country(CountryDTO.convertDtoToCountry(stateDto.getCountry()))
			        .cities(CityDTO.convertListDtoToListCities(stateDto.getCities()))
			        .build();
	}

	public static List<StateDTO> convertListStatesToListDto(List<State> states) {
		List<StateDTO> stateDTOs = new ArrayList<>();
		if (!CollectionUtils.isEmpty(states)) {
			states.forEach(state -> stateDTOs.add(convertStateToDto(state)));
		}
		return stateDTOs;
	}

	public static List<State> convertListDtoToListStates(List<StateDTO> statesDtos) {
		List<State> states = new ArrayList<>();
		if (!CollectionUtils.isEmpty(statesDtos)) {
			statesDtos.forEach(dto -> states.add(convertDtoToState(dto)));
		}
		return states;
	}

}