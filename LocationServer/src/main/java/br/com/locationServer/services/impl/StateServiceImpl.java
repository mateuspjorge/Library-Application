package br.com.locationServer.services.impl;

import java.util.List;
import java.util.Objects;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import br.com.locationServer.dtos.CountryDTO;
import br.com.locationServer.dtos.StateDTO;
import br.com.locationServer.entitys.State;
import br.com.locationServer.exception.StateException;
import br.com.locationServer.repositorys.IStateRepository;
import br.com.locationServer.services.IStateService;

@Service
public class StateServiceImpl implements IStateService {

	private static final String MSG_ERROR_NENHUM_REGISTRO_ENCONTRADO = "Nenhum registro foi encontrado.";

	private IStateRepository stateRepository;

	@Autowired
	public StateServiceImpl(IStateRepository stateRepository) {
		super();
		this.stateRepository = stateRepository;
	}

	@Override
	public State registerState(StateDTO stateDTO) throws StateException {
		validateState(stateDTO);
		return stateRepository.save(StateDTO.convertDtoToState(stateDTO));
	}

	@Override
	public List<State> searchAllStates() throws StateException {
		List<State> statesFound = stateRepository.findAll();
		if (CollectionUtils.isEmpty(statesFound)) {
			throw new StateException(MSG_ERROR_NENHUM_REGISTRO_ENCONTRADO);
		}
		return statesFound;
	}

	@Override
	public State searchStateByNameAndInitials(StateDTO stateDTO) throws StateException {
		validateState(stateDTO);
		State stateFound = stateRepository.findByNameAndInitials(stateDTO.getName().toUpperCase(),
																 stateDTO.getInitials().toUpperCase());
		if (Objects.isNull(stateFound)) {
			throw new StateException(MSG_ERROR_NENHUM_REGISTRO_ENCONTRADO);
		}
		return stateFound;
	}

	@Override
	public State searchStateById(Long stateId) throws StateException {
		State stateFound = stateRepository.findById(stateId).orElse(null);
		if (Objects.isNull(stateFound)) {
			throw new StateException(MSG_ERROR_NENHUM_REGISTRO_ENCONTRADO);
		}
		return stateFound;
	}

	@Override
	public State updateState(StateDTO stateDTO) throws StateException {
		State stateFound = searchStateByNameAndInitials(stateDTO);
		if (Objects.nonNull(stateDTO.getNewCountry())) {
			stateFound.setCountry(CountryDTO.convertDtoToCountry(stateDTO.getNewCountry()));
		}
		if (StringUtils.isNotBlank(stateDTO.getNewName())) {
			stateFound.setName(stateDTO.getNewName());
		}
		if (StringUtils.isNotBlank(stateDTO.getNewInitials())) {
			stateFound.setInitials(stateDTO.getNewInitials());
		}
		return stateRepository.save(stateFound);
	}

	@Override
	public void deleteState(StateDTO stateDTO) throws StateException {
		stateRepository.delete(searchStateByNameAndInitials(stateDTO));
	}

	protected void validateState(StateDTO stateDTO) throws StateException {
		if (Objects.isNull(stateDTO.getCountry())) {
			throw new StateException("O País informado é inválido.");
		}
		if (StringUtils.isBlank(stateDTO.getName())) {
			throw new StateException("O nome informado é inválido.");
		}
		if (StringUtils.isBlank(stateDTO.getInitials())) {
			throw new StateException("As iniciais digitadas são inválidas.");
		}
	}

}