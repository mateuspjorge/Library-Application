package br.com.locationServer.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import br.com.locationServer.dtos.AddressDTO;
import br.com.locationServer.exception.AddressException;
import br.com.locationServer.services.impl.AddressServiceImpl;

@RestController
@RequestMapping(path = "/address")
public class AddressControllerImpl {

	private AddressServiceImpl addressService;

	@Autowired
	public AddressControllerImpl(AddressServiceImpl addressService) {
		super();
		this.addressService = addressService;
	}

	@PostMapping("/register")
	@ResponseBody
	public ResponseEntity<AddressDTO> registerAddress(@RequestBody AddressDTO addressDTO) throws AddressException {
		AddressDTO registeredAddress = AddressDTO.convertAddressToDto(addressService.registerAddress(addressDTO));
		return new ResponseEntity<>(registeredAddress, HttpStatus.CREATED);
	}

	@GetMapping("/search")
	@ResponseBody
	public ResponseEntity<List<AddressDTO>> searchAllAddress() throws AddressException {
		List<AddressDTO> addressFound = AddressDTO.convertListAddressToListDto(addressService.searchAllAddress());
		return new ResponseEntity<>(addressFound, HttpStatus.ACCEPTED);
	}

	@GetMapping("/internal/search-by/{addressId}")
	@ResponseBody
	public ResponseEntity<AddressDTO> searchAddressById(@PathVariable("addressId") Long addressId) throws AddressException {
		AddressDTO addressFound = AddressDTO.convertAddressToDto(addressService.searchAddressById(addressId));
		return new ResponseEntity<>(addressFound, HttpStatus.ACCEPTED);
	}

	@PutMapping("/update")
	@ResponseBody
	public ResponseEntity<AddressDTO> updateAddress(@RequestBody AddressDTO addressDTO) throws AddressException {
		AddressDTO updatedAddress = AddressDTO.convertAddressToDto(addressService.updateAddress(addressDTO));
		return new ResponseEntity<>(updatedAddress, HttpStatus.CREATED);
	}

	@DeleteMapping("/delete")
	@ResponseBody
	public void deleteAddress(@RequestBody AddressDTO addressDTO) throws AddressException {
		addressService.deleteAddress(addressDTO);
	}

}