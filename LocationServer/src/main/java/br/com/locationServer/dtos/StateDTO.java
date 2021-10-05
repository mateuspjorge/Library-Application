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

	private Long countryId;

	private Long newCountryId;

	private String name;

	private String newName;

	private String initials;

	private String newInitials;

	private CountryDTO country;

	public static StateDTO convertStateToDto(State state) {
		return StateDTO.builder()
				       .id(state.getId())
				       .countryId(state.getCountryId())
				       .name(state.getName())
				       .initials(state.getInitials())
				       .build();
	}

	public static List<StateDTO> convertListStatesToListDto(List<State> states) {
		List<StateDTO> stateDTOs = new ArrayList<>();
		if (!CollectionUtils.isEmpty(states)) {
			states.forEach(state -> stateDTOs.add(StateDTO.builder()
														  .id(state.getId())
														  .countryId(state.getCountryId())
														  .name(state.getName())
														  .initials(state.getInitials())
														  .build()));
		}
		return stateDTOs;
	}

}