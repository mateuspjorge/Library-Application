package br.com.locationServer.services;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import br.com.locationServer.dtos.CountryDTO;
import br.com.locationServer.dtos.StateDTO;
import br.com.locationServer.entitys.State;
import br.com.locationServer.exception.StateException;
import br.com.locationServer.repositorys.IStateRepository;

@Service
public class StateService {

	private static final String MSG_ERROR_NENHUM_REGISTRO_ENCONTRADO = "Nenhum registro foi encontrado.";

	private IStateRepository stateRepository;

	@Autowired
	public StateService(IStateRepository stateRepository) {
		super();
		this.stateRepository = stateRepository;
	}

	public State registerState(StateDTO stateDTO) throws StateException {
		validateState(stateDTO);
		return stateRepository.save(State.builder()
										 .name(stateDTO.getName())
										 .initials(stateDTO.getInitials())
										 .country(CountryDTO.con)
										 .build());
	}

	public List<State> searchAllStates() throws StateException {
		List<State> statesFound = stateRepository.findAll();
		if (CollectionUtils.isEmpty(statesFound)) {
			throw new StateException(MSG_ERROR_NENHUM_REGISTRO_ENCONTRADO);
		}
		return statesFound;
	}

	public State searchStateByCountryAndNameAndInitials(StateDTO stateDTO) throws StateException {
		validateState(stateDTO);
		State stateFound = stateRepository.findByCountryIdAndNameAndInitials(stateDTO.getCountryId(),
																			 stateDTO.getName(),
																			 stateDTO.getInitials());
		if (ObjectUtils.isEmpty(stateFound)) {
			throw new StateException(MSG_ERROR_NENHUM_REGISTRO_ENCONTRADO);
		}
		return stateFound;
	}

	public State updateState(StateDTO stateDTO) throws StateException {
		State stateFound = searchStateByCountryAndNameAndInitials(stateDTO);
		stateFound.setCountryId(stateDTO.getNewCountryId());
		stateFound.setName(stateDTO.getNewName());
		stateFound.setInitials(stateDTO.getNewInitials());
		return stateRepository.save(stateFound);
	}

	public void deleteState(StateDTO stateDTO) throws StateException {
		stateRepository.delete(searchStateByCountryAndNameAndInitials(stateDTO));
	}

	public State searchStateById(Long stateId) throws StateException {
		State stateFound = stateRepository.findById(stateId).orElse(null);
		if (ObjectUtils.isEmpty(stateFound)) {
			throw new StateException(MSG_ERROR_NENHUM_REGISTRO_ENCONTRADO);
		}
		return stateFound;
	}

	protected void validateState(StateDTO stateDTO) throws StateException {
		if (ObjectUtils.isEmpty(stateDTO.getCountry())) {
			throw new StateException("O País informado é inválido.");
		}
		if (StringUtils.isEmpty(stateDTO.getName())) {
			throw new StateException("O nome informado é inválido.");
		}
		if (StringUtils.isEmpty(stateDTO.getInitials())) {
			throw new StateException("As iniciais digitadas são inválidas.");
		}
	}

}