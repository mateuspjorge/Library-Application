package br.com.locationServer.controllers.impl;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;

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
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;

import br.com.locationServer.controllers.CountryController;
import br.com.locationServer.dtos.CountryDTO;
import br.com.locationServer.exceptions.CountryException;
import br.com.locationServer.services.ICountryService;

@RestController
@RequestMapping(path = "/country")
public class CountryControllerImpl implements CountryController {

	private ICountryService countryService;

	@Autowired
	public CountryControllerImpl(ICountryService countryService) {
		super();
		this.countryService = countryService;
	}

	@PostMapping("/register")
	@ResponseBody
	@Override
	public ResponseEntity<CountryDTO> registerCountry(@RequestBody CountryDTO countryDTO) {
		try {
			return ResponseEntity.ok(CountryDTO.convertCountryToDto(countryService.registerCountry(countryDTO)));
		} catch (CountryException exception) {
			exception.printStackTrace();
			return ResponseEntity.status(406).body(CountryDTO.createDtoWithMessageError(exception.getMessage()));
		}
	}

	@GetMapping("/search")
	@ResponseBody
	@Override
	public Callable<ResponseEntity<List<CountryDTO>>> searchAllCountries() {
		return () -> {
			try {
				return ResponseEntity.ok(CountryDTO.convertListCountriesToListDto(countryService.searchAllCountries()));
			} catch (CountryException exception) {
				exception.printStackTrace();
				return ResponseEntity.status(204).body(Arrays.asList(CountryDTO.createDtoWithMessageError(exception.getMessage())));
			}  catch (AsyncRequestTimeoutException exception) {
				exception.printStackTrace();
				return ResponseEntity.status(204).body(Arrays.asList(CountryDTO.createDtoWithMessageError("tESTED")));
			}
		};
	}

	@GetMapping("/internal/search-by/{countryId}")
	@ResponseBody
	@Override
	public ResponseEntity<CountryDTO> searchCountryById(@PathVariable("countryId") Long countryId) {
		try {
			return ResponseEntity.ok(CountryDTO.convertCountryToDto(countryService.searchCountryById(countryId)));
		} catch (CountryException exception) {
			exception.printStackTrace();
			return ResponseEntity.status(204).body(CountryDTO.createDtoWithMessageError(exception.getMessage()));
		}
	}

	@PutMapping("/update")
	@ResponseBody
	@Override
	public ResponseEntity<CountryDTO> updateCountry(@RequestBody CountryDTO countryDTO) {
		try {
			return ResponseEntity.ok(CountryDTO.convertCountryToDto(countryService.updateCountry(countryDTO)));
		} catch (CountryException exception) {
			exception.printStackTrace();
			return ResponseEntity.status(406).body(CountryDTO.createDtoWithMessageError(exception.getMessage()));
		}
	}

	@DeleteMapping("/delete")
	@ResponseBody
	@Override
	public ResponseEntity<CountryDTO> deleteCountry(@RequestBody CountryDTO countryDTO) {
		try {
			countryService.deleteCountry(countryDTO.getName());
			return ResponseEntity.status(200).body(CountryDTO.createDtoWithMessageError(null));
		} catch (CountryException exception) {
			exception.printStackTrace();
			return ResponseEntity.status(406).body(CountryDTO.createDtoWithMessageError(exception.getMessage()));
		}
	}

}