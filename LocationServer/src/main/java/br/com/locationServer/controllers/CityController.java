package br.com.locationServer.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
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
import br.com.locationServer.entitys.City;
import br.com.locationServer.exception.CityException;
import br.com.locationServer.services.CityService;

@RestController
@RequestMapping(path = "/city")
public class CityController {

	private CityService cityService;

	@Autowired
	public CityController(CityService cityService) {
		super();
		this.cityService = cityService;
	}

	@PostMapping("/register")
	@ResponseBody
	public ResponseEntity<Boolean> registerCity(@RequestBody CityDTO cityDTO) throws CityException {
		return new ResponseEntity<>(!ObjectUtils.isEmpty(cityService.registerCity(cityDTO)), HttpStatus.CREATED);
	}

	@GetMapping("/search")
	@ResponseBody
	public ResponseEntity<List<CityDTO>> searchAllCities() throws CityException {
		return new ResponseEntity<>(CityDTO.convertListCitiesToListDto(cityService.searchAllCities()), HttpStatus.ACCEPTED);
	}

	@GetMapping("/internal/search-by/{id}")
	@ResponseBody
	public ResponseEntity<List<CityDTO>> searchCityById(@PathVariable("cityId") Long cityId) throws CityException {
		City cityFound = cityService.searchCityById(cityId);
		return new ResponseEntity<>(CityDTO.convertListCitiesToListDto(cityService.searchAllCities()), HttpStatus.ACCEPTED);
	}

	@PutMapping("/update")
	@ResponseBody
	public ResponseEntity<Boolean> updateCity(@RequestBody CityDTO cityDTO) throws CityException {
		return new ResponseEntity<>(!ObjectUtils.isEmpty(cityService.updateCity(cityDTO)), HttpStatus.CREATED);
	}

	@DeleteMapping("/delete")
	@ResponseBody
	public void deleteCity(@RequestBody CityDTO cityDTO) throws CityException {
		cityService.deleteCity(cityDTO);
	}

}