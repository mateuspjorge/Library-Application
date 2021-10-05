package br.com.locationServer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.locationServer.clients.ICityFeign;
import br.com.locationServer.dtos.AddressDTO;
import br.com.locationServer.exception.AddressException;
import br.com.locationServer.exception.CityException;
import br.com.locationServer.exception.StateException;
import br.com.locationServer.services.AddressService;

@RestController
@RequestMapping(path = "/address")
public class AddressController {

	private AddressService addressService;
	private ICityFeign iCityFeign;

	@Autowired
	public AddressController(AddressService addressService, ICityFeign iCityFeign) {
		super();
		this.addressService = addressService;
		this.iCityFeign = iCityFeign;
	}

	@PostMapping("/register")
	@ResponseBody
	public ResponseEntity<AddressDTO> registerAddress(@RequestBody AddressDTO addressDTO) throws AddressException, CityException, StateException {
		AddressDTO registeredAddress = AddressDTO.convertAddressToDto(addressService.registerAddress(addressDTO));
		registeredAddress.setCity(iCityFeign.searchCityById(registeredAddress.getCityId()).getBody());
		return new ResponseEntity<>(registeredAddress, HttpStatus.CREATED);
	}

}