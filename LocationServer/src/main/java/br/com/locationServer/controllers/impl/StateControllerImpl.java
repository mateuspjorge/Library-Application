package br.com.locationServer.controllers.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public ResponseEntity<StateDTO> registerState(@RequestBody StateDTO stateDTO) {
		try {
			return ResponseEntity.ok(StateDTO.convertStateToDto(stateService.registerState(stateDTO)));
		} catch (StateException exception) {
			exception.printStackTrace();
			return ResponseEntity.status(406).body(StateDTO.createDtoWithMessageError(exception.getMessage()));
		}
	}

	@GetMapping("/search")
	@ResponseBody
	@Override
	public ResponseEntity<List<StateDTO>> searchAllStates() {
		try {
			return ResponseEntity.ok(StateDTO.convertListStatesToListDto(stateService.searchAllStates()));
		} catch (StateException exception) {
			exception.printStackTrace();
			return ResponseEntity.status(204).body(Arrays.asList(StateDTO.createDtoWithMessageError(exception.getMessage())));
		}
	}

	@GetMapping("/internal/search-by/{stateId}")
	@ResponseBody
	@Override
	public ResponseEntity<StateDTO> searchStateById(@PathVariable("stateId") Long stateId) {
		try {
			return ResponseEntity.ok(StateDTO.convertStateToDto(stateService.searchStateById(stateId)));
		} catch (StateException exception) {
			exception.printStackTrace();
			return ResponseEntity.status(204).body(StateDTO.createDtoWithMessageError(exception.getMessage()));
		}
	}

	@PutMapping("/update")
	@ResponseBody
	@Override
	public ResponseEntity<StateDTO> updateState(@RequestBody StateDTO stateDTO) {
		try {
			return ResponseEntity.ok(StateDTO.convertStateToDto(stateService.updateState(stateDTO)));
		} catch (StateException exception) {
			exception.printStackTrace();
			return ResponseEntity.status(406).body(StateDTO.createDtoWithMessageError(exception.getMessage()));
		}
	}

	@DeleteMapping("/delete")
	@ResponseBody
	@Override
	public ResponseEntity<StateDTO> deleteState(@RequestBody StateDTO stateDTO) {
		try {
			stateService.deleteState(stateDTO);
			return ResponseEntity.status(200).body(StateDTO.createDtoWithMessageError(null));
		} catch (StateException exception) {
			exception.printStackTrace();
			return ResponseEntity.status(406).body(StateDTO.createDtoWithMessageError(exception.getMessage()));
		}
	}

}