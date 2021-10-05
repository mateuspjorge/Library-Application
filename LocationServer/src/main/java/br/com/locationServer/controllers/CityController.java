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

import br.com.locationServer.clients.IStateFeign;
import br.com.locationServer.dtos.CityDTO;
import br.com.locationServer.exception.CityException;
import br.com.locationServer.exception.CountryException;
import br.com.locationServer.exception.StateException;
import br.com.locationServer.services.CityService;

@RestController
@RequestMapping(path = "/city")
public class CityController {

	private CityService cityService;
	private IStateFeign iStateFeign;

	@Autowired
	public CityController(CityService cityService, IStateFeign iStateFeign) {
		super();
		this.cityService = cityService;
		this.iStateFeign = iStateFeign;
	}

	@PostMapping("/register")
	@ResponseBody
	public ResponseEntity<CityDTO> registerCity(@RequestBody CityDTO cityDTO) throws CityException, StateException, CountryException {
		CityDTO registeredCity = CityDTO.convertCityToDto(cityService.registerCity(cityDTO));
		registeredCity.setState(iStateFeign.searchStateById(registeredCity.getStateId()).getBody());
		return new ResponseEntity<>(registeredCity, HttpStatus.CREATED);
	}

	@GetMapping("/search")
	@ResponseBody
	public ResponseEntity<List<CityDTO>> searchAllCities() throws CityException {
		List<CityDTO> citiesFound = CityDTO.convertListCitiesToListDto(cityService.searchAllCities());
		citiesFound.forEach(cityDto -> {
			try {
				cityDto.setState(iStateFeign.searchStateById(cityDto.getStateId()).getBody());
			} catch (StateException e) {
				e.printStackTrace();
			} catch (CountryException e) {
				e.printStackTrace();
			}
		});
		return new ResponseEntity<>(citiesFound, HttpStatus.ACCEPTED);
	}

	@GetMapping("/internal/search-by/{cityId}")
	@ResponseBody
	public ResponseEntity<CityDTO> searchCityById(@PathVariable("cityId") Long cityId) throws CityException, StateException, CountryException {
		CityDTO cityDTO = CityDTO.convertCityToDto(cityService.searchCityById(cityId));
		cityDTO.setState(iStateFeign.searchStateById(cityDTO.getStateId()).getBody());
		return new ResponseEntity<>(cityDTO, HttpStatus.ACCEPTED);
	}

	@PutMapping("/update")
	@ResponseBody
	public ResponseEntity<CityDTO> updateCity(@RequestBody CityDTO cityDTO) throws CityException, StateException, CountryException {
		CityDTO updatedCity = CityDTO.convertCityToDto(cityService.updateCity(cityDTO));
		updatedCity.setState(iStateFeign.searchStateById(updatedCity.getStateId()).getBody());
		return new ResponseEntity<>(updatedCity, HttpStatus.CREATED);
	}

	@DeleteMapping("/delete")
	@ResponseBody
	public void deleteCity(@RequestBody CityDTO cityDTO) throws CityException {
		cityService.deleteCity(cityDTO);
	}

}