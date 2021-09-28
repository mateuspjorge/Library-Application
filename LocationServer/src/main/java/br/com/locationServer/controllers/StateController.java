package br.com.locationServer.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.locationServer.dtos.StateDTO;
import br.com.locationServer.entitys.State;
import br.com.locationServer.exception.StateException;
import br.com.locationServer.services.StateService;

@RestController
@RequestMapping(path = "/state")
public class StateController {

	private StateService stateService;

	@Autowired
	public StateController(StateService stateService) {
		super();
		this.stateService = stateService;
	}

	@PostMapping("/register")
	@ResponseBody
	public ResponseEntity<Boolean> stateRegister(@RequestBody StateDTO stateDTO) throws StateException {
		State registeredState = stateService.stateRegister(stateDTO);
		return new ResponseEntity<Boolean>(!ObjectUtils.isEmpty(registeredState), HttpStatus.CREATED);
	}

	@GetMapping("/search")
	@ResponseBody
	public ResponseEntity<List<StateDTO>> searchAllStates() throws StateException {
		List<State> statesFound = stateService.searchAllStates();
		List<StateDTO> stateDTOs = new ArrayList<>();
		if (!CollectionUtils.isEmpty(statesFound)) {
			statesFound.forEach(state -> stateDTOs.add(StateDTO.builder()
															   .countryId(state.getCountryId())
															   .name(state.getName())
															   .initials(state.getInitials())
															   .build()));
		}
		return new ResponseEntity<List<StateDTO>>(stateDTOs, HttpStatus.ACCEPTED);
	}

	@PutMapping("/update")
	@ResponseBody
	public ResponseEntity<Boolean> updateState(@RequestBody StateDTO stateDTO) throws StateException {
		State updatedState = stateService.updateState(stateDTO);
		return new ResponseEntity<Boolean>(!ObjectUtils.isEmpty(updatedState), HttpStatus.CREATED);
	}

	@DeleteMapping("/delete")
	@ResponseBody
	public void deleteState(@RequestBody StateDTO stateDTO) throws StateException {
		stateService.deleteState(stateDTO);
	}

}