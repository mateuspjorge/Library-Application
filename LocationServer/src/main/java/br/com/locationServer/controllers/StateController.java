package br.com.locationServer.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<Boolean> registerState(@RequestBody StateDTO stateDTO) throws StateException {
		return new ResponseEntity<>(!ObjectUtils.isEmpty(stateService.registerState(stateDTO)), HttpStatus.CREATED);
	}

	@GetMapping("/search")
	@ResponseBody
	public ResponseEntity<List<StateDTO>> searchAllStates() throws StateException {
		return new ResponseEntity<>(StateDTO.convertListStatesToListDto(stateService.searchAllStates()), HttpStatus.ACCEPTED);
	}

	@PutMapping("/update")
	@ResponseBody
	public ResponseEntity<Boolean> updateState(@RequestBody StateDTO stateDTO) throws StateException {
		return new ResponseEntity<>(!ObjectUtils.isEmpty(stateService.updateState(stateDTO)), HttpStatus.CREATED);
	}

	@DeleteMapping("/delete")
	@ResponseBody
	public void deleteState(@RequestBody StateDTO stateDTO) throws StateException {
		stateService.deleteState(stateDTO);
	}

}