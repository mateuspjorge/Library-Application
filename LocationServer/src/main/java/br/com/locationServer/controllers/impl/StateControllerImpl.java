package br.com.locationServer.controllers.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.locationServer.controllers.StateController;
import br.com.locationServer.dtos.StateDTO;
import br.com.locationServer.exception.StateException;
import br.com.locationServer.services.IStateService;

@RestController
@RequestMapping(path = "/state")
public class StateControllerImpl implements StateController {

	private IStateService stateService;

	@Autowired
	public StateControllerImpl(IStateService stateService) {
		super();
		this.stateService = stateService;
	}

	@PostMapping("/register")
	@ResponseBody
	@Override
	public ResponseEntity<StateDTO> registerState(@RequestBody StateDTO stateDTO) throws StateException {
		return new ResponseEntity<>(StateDTO.convertStateToDto(stateService.registerState(stateDTO)), HttpStatus.CREATED);
	}

	@GetMapping("/search")
	@ResponseBody
	@Override
	public ResponseEntity<List<StateDTO>> searchAllStates() throws StateException {
		return new ResponseEntity<>(StateDTO.convertListStatesToListDto(stateService.searchAllStates()), HttpStatus.ACCEPTED);
	}

	@GetMapping("/internal/search-by/{stateId}")
	@ResponseBody
	@Override
	public ResponseEntity<StateDTO> searchStateById(@PathVariable("stateId") Long stateId) throws StateException {
		return new ResponseEntity<>(StateDTO.convertStateToDto(stateService.searchStateById(stateId)), HttpStatus.OK);
	}

	@PutMapping("/update")
	@ResponseBody
	@Override
	public ResponseEntity<StateDTO> updateState(@RequestBody StateDTO stateDTO) throws StateException {
		return new ResponseEntity<>(StateDTO.convertStateToDto(stateService.updateState(stateDTO)), HttpStatus.CREATED);
	}

	@DeleteMapping("/delete")
	@ResponseBody
	@Override
	public void deleteState(@RequestBody StateDTO stateDTO) throws StateException {
		stateService.deleteState(stateDTO);
	}

}