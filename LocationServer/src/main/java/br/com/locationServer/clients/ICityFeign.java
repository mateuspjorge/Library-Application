package br.com.locationServer.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.locationServer.dtos.CityDTO;
import br.com.locationServer.exception.CityException;
import br.com.locationServer.exception.StateException;

@FeignClient(value = "http://location-server/city")
public interface ICityFeign {

	@GetMapping("/internal/search-by/{cityId}")
	@ResponseBody
	public ResponseEntity<CityDTO> searchCityById(@PathVariable("cityId") Long cityId) throws CityException, StateException;

}