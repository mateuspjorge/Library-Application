package br.com.locationServer.controllers;

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

import br.com.locationServer.clients.ICountryFeign;
import br.com.locationServer.dtos.StateDTO;
import br.com.locationServer.exception.CountryException;
import br.com.locationServer.exception.StateException;
import br.com.locationServer.services.StateService;

@RestController
@RequestMapping(path = "/state")
public class StateController {

	private StateService stateService;
	private ICountryFeign iCountryFeign;

	@Autowired
	public StateController(StateService stateService, ICountryFeign iCountryFeign) {
		super();
		this.stateService = stateService;
		this.iCountryFeign = iCountryFeign;
	}

	@PostMapping("/register")
	@ResponseBody
	public ResponseEntity<StateDTO> registerState(@RequestBody StateDTO stateDTO) throws StateException, CountryException {
		StateDTO registeredState = StateDTO.convertStateToDto(stateService.registerState(stateDTO));
		registeredState.setCountry(iCountryFeign.searchCountryById(registeredState.getCountryId()).getBody());
		return new ResponseEntity<>(registeredState, HttpStatus.CREATED);
	}

	@GetMapping("/search")
	@ResponseBody
	public ResponseEntity<List<StateDTO>> searchAllStates() throws StateException {
		List<StateDTO> statesFound = StateDTO.convertListStatesToListDto(stateService.searchAllStates());
		statesFound.forEach(stateDto -> {
			try {
				stateDto.setCountry(iCountryFeign.searchCountryById(stateDto.getCountryId()).getBody());
			} catch (CountryException e) {
				e.printStackTrace();
			}
		});
		return new ResponseEntity<>(statesFound, HttpStatus.ACCEPTED);
	}

	@GetMapping("/internal/search-by/{stateId}")
	@ResponseBody
	public ResponseEntity<StateDTO> searchStateById(@PathVariable("stateId") Long stateId) throws StateException, CountryException {
		StateDTO stateFound = StateDTO.convertStateToDto(stateService.searchStateById(stateId));
		stateFound.setCountry(iCountryFeign.searchCountryById(stateFound.getCountryId()).getBody());
		return new ResponseEntity<>(stateFound, HttpStatus.OK);
	}

	@PutMapping("/update")
	@ResponseBody
	public ResponseEntity<StateDTO> updateState(@RequestBody StateDTO stateDTO) throws StateException, CountryException {
		StateDTO updatedState = StateDTO.convertStateToDto(stateService.updateState(stateDTO));
		updatedState.setCountry(iCountryFeign.searchCountryById(updatedState.getCountryId()).getBody());
		return new ResponseEntity<>(updatedState, HttpStatus.CREATED);
	}

	@DeleteMapping("/delete")
	@ResponseBody
	public void deleteState(@RequestBody StateDTO stateDTO) throws StateException {
		stateService.deleteState(stateDTO);
	}

}