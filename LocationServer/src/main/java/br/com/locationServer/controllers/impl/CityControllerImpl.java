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

import br.com.locationServer.controllers.CityController;
import br.com.locationServer.dtos.CityDTO;
import br.com.locationServer.exceptions.CityException;
import br.com.locationServer.services.ICityService;

@RestController
@RequestMapping(path = "/city")
public class CityControllerImpl implements CityController {

	private ICityService cityService;

	@Autowired
	public CityControllerImpl(ICityService cityService) {
		super();
		this.cityService = cityService;
	}

	@PostMapping("/register")
	@ResponseBody
	@Override
	public ResponseEntity<CityDTO> registerCity(@RequestBody CityDTO cityDTO) {
		try {
			return ResponseEntity.ok(CityDTO.convertCityToDto(cityService.registerCity(cityDTO)));
		} catch (CityException exception) {
			exception.printStackTrace();
			return ResponseEntity.status(406).body(CityDTO.createDtoWithMessageError(exception.getMessage()));
		}
	}

	@GetMapping("/search")
	@ResponseBody
	@Override
	public ResponseEntity<List<CityDTO>> searchAllCities() {
		try {
			return ResponseEntity.ok(CityDTO.convertListCitiesToListDto(cityService.searchAllCities()));
		} catch (CityException exception) {
			exception.printStackTrace();
			return ResponseEntity.status(204).body(Arrays.asList(CityDTO.createDtoWithMessageError(exception.getMessage())));
		}
	}

	@GetMapping("/internal/search-by/{cityId}")
	@ResponseBody
	@Override
	public ResponseEntity<CityDTO> searchCityById(@PathVariable("cityId") Long cityId) {
		try {
			return ResponseEntity.ok(CityDTO.convertCityToDto(cityService.searchCityById(cityId)));
		} catch (CityException exception) {
			exception.printStackTrace();
			return ResponseEntity.status(204).body(CityDTO.createDtoWithMessageError(exception.getMessage()));
		}
	}

	@PutMapping("/update")
	@ResponseBody
	@Override
	public ResponseEntity<CityDTO> updateCity(@RequestBody CityDTO cityDTO) {
		try {
			return ResponseEntity.ok(CityDTO.convertCityToDto(cityService.updateCity(cityDTO)));
		} catch (CityException exception) {
			exception.printStackTrace();
			return ResponseEntity.status(406).body(CityDTO.createDtoWithMessageError(exception.getMessage()));
		}
	}

	@DeleteMapping("/delete")
	@ResponseBody
	@Override
	public ResponseEntity<CityDTO> deleteCity(@RequestBody CityDTO cityDTO) {
		try {
			cityService.deleteCity(cityDTO);
			return ResponseEntity.status(200).body(CityDTO.createDtoWithMessageError(null));
		} catch (CityException exception) {
			exception.printStackTrace();
			return ResponseEntity.status(406).body(CityDTO.createDtoWithMessageError(exception.getMessage()));
		}
	}

}