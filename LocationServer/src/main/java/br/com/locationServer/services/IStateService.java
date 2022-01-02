package br.com.locationServer.services;

import java.util.List;

import br.com.locationServer.dtos.StateDTO;
import br.com.locationServer.entitys.State;
import br.com.locationServer.exceptions.StateException;

public interface IStateService {

	public State registerState(StateDTO stateDTO) throws StateException;

	public List<State> searchAllStates() throws StateException;

	public State searchStateByNameAndInitials(StateDTO stateDTO) throws StateException;

	public State searchStateById(Long stateId) throws StateException;

	public State updateState(StateDTO stateDTO) throws StateException;

	public void deleteState(StateDTO stateDTO) throws StateException;

}