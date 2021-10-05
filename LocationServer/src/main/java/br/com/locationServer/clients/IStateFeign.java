package br.com.locationServer.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.locationServer.dtos.StateDTO;
import br.com.locationServer.exception.CountryException;
import br.com.locationServer.exception.StateException;

@FeignClient(value = "http://location-server/state")
public interface IStateFeign {

	@GetMapping("/internal/search-by/{stateId}")
	@ResponseBody
	public ResponseEntity<StateDTO> searchStateById(@PathVariable("stateId") Long stateId) throws StateException, CountryException;

}