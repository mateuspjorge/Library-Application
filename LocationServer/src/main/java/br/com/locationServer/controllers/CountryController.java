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

import br.com.locationServer.dtos.CountryDTO;
import br.com.locationServer.exception.CountryException;
import br.com.locationServer.services.CountryService;

@RestController
@RequestMapping(path = "/country")
public class CountryController {

	private CountryService countryService;

	@Autowired
	public CountryController(CountryService countryService) {
		super();
		this.countryService = countryService;
	}

	@PostMapping("/register")
	@ResponseBody
	public ResponseEntity<Boolean> registerCountry(@RequestBody CountryDTO countryDTO) throws CountryException {
		return new ResponseEntity<>(!ObjectUtils.isEmpty(countryService.registerCountry(countryDTO.getName())), HttpStatus.CREATED);
	}

	@GetMapping("/search")
	@ResponseBody
	public ResponseEntity<List<CountryDTO>> searchAllCountries() throws CountryException {
		return new ResponseEntity<>(CountryDTO.convertListCountriesToListDto(countryService.searchAllCountries()), HttpStatus.ACCEPTED);
	}

	@PutMapping("/update")
	@ResponseBody
	public ResponseEntity<Boolean> updateCountry(@RequestBody CountryDTO countryDTO) throws CountryException {
		return new ResponseEntity<>(!ObjectUtils.isEmpty(countryService.updateCountry(countryDTO)), HttpStatus.CREATED);
	}

	@DeleteMapping("/delete")
	@ResponseBody
	public void deleteCountry(@RequestBody CountryDTO countryDTO) throws CountryException {
		countryService.deleteCountry(countryDTO.getName());
	}

}