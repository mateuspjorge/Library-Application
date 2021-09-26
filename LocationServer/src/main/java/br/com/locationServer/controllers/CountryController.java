package br.com.locationServer.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.locationServer.dtos.CountryDTO;
import br.com.locationServer.entitys.Country;
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
	public ResponseEntity<Boolean> countryRegister(CountryDTO countryDTO) {
		Country registeredCountry = countryService.countryRegister(countryDTO.getName());
		return new ResponseEntity<Boolean>(!ObjectUtils.isEmpty(registeredCountry), HttpStatus.CREATED);
	}

	@GetMapping("/search")
	@ResponseBody
	public ResponseEntity<List<CountryDTO>> searchAllCountries() {
		List<Country> countriesFound = countryService.searchAllCountries();
		List<CountryDTO> countryDTOs = new ArrayList<>();
		if (!CollectionUtils.isEmpty(countriesFound)) {
			countriesFound.forEach(country -> countryDTOs.add(CountryDTO.builder().name(country.getName()).build()));
		}
		return new ResponseEntity<List<CountryDTO>>(countryDTOs, HttpStatus.ACCEPTED);
	}

	@PutMapping("/update")
	@ResponseBody
	public ResponseEntity<Boolean> updateCountry(CountryDTO countryDTO) {
		Country updatedCountry = countryService.updateCountry(countryDTO);
		return new ResponseEntity<Boolean>(!ObjectUtils.isEmpty(updatedCountry), HttpStatus.CREATED);
	}

}