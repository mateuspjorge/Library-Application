package br.com.locationServer.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.locationServer.dtos.CountryDTO;
import br.com.locationServer.exception.CountryException;

@FeignClient(value = "http://location-server/country")
public interface ICountryFeign {

	@GetMapping("/internal/search-by/{countryId}")
	@ResponseBody
	public ResponseEntity<CountryDTO> searchCountryById(@PathVariable("countryId") Long countryId) throws CountryException;

}