package br.com.locationServer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.locationServer.dtos.AddressDTO;
import br.com.locationServer.entitys.Address;
import br.com.locationServer.exception.AddressException;
import br.com.locationServer.services.AddressService;

@RestController
@RequestMapping(path = "/address")
public class AddressController {

	private AddressService addressService;

	@Autowired
	public AddressController(AddressService addressService) {
		super();
		this.addressService = addressService;
	}

	@PostMapping("/register")
	@ResponseBody
	public ResponseEntity<AddressDTO> registerAddress(@RequestBody AddressDTO addressDTO) throws AddressException {
		Address registeredAddress = addressService.registerAddress(addressDTO);
		AddressDTO dtoRetorno = AddressDTO.convertAddressToDto(registeredAddress);
		return new ResponseEntity<>(!ObjectUtils.isEmpty(), HttpStatus.CREATED);
	}

}