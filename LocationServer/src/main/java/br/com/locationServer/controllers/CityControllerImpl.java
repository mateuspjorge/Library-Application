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

import br.com.locationServer.dtos.CityDTO;
import br.com.locationServer.exception.CityException;
import br.com.locationServer.services.impl.CityServiceImpl;

@RestController
@RequestMapping(path = "/city")
public class CityControllerImpl {

	private CityServiceImpl cityService;

	@Autowired
	public CityControllerImpl(CityServiceImpl cityService) {
		super();
		this.cityService = cityService;
	}

	@PostMapping("/register")
	@ResponseBody
	public ResponseEntity<CityDTO> registerCity(@RequestBody CityDTO cityDTO) throws CityException {
		return new ResponseEntity<>(CityDTO.convertCityToDto(cityService.registerCity(cityDTO)), HttpStatus.CREATED);
	}

	@GetMapping("/search")
	@ResponseBody
	public ResponseEntity<List<CityDTO>> searchAllCities() throws CityException {
		return new ResponseEntity<>(CityDTO.convertListCitiesToListDto(cityService.searchAllCities()), HttpStatus.ACCEPTED);
	}

	@GetMapping("/internal/search-by/{cityId}")
	@ResponseBody
	public ResponseEntity<CityDTO> searchCityById(@PathVariable("cityId") Long cityId) throws CityException {
		return new ResponseEntity<>(CityDTO.convertCityToDto(cityService.searchCityById(cityId)), HttpStatus.ACCEPTED);
	}

	@PutMapping("/update")
	@ResponseBody
	public ResponseEntity<CityDTO> updateCity(@RequestBody CityDTO cityDTO) throws CityException {
		return new ResponseEntity<>(CityDTO.convertCityToDto(cityService.updateCity(cityDTO)), HttpStatus.CREATED);
	}

	@DeleteMapping("/delete")
	@ResponseBody
	public void deleteCity(@RequestBody CityDTO cityDTO) throws CityException {
		cityService.deleteCity(cityDTO);
	}

}