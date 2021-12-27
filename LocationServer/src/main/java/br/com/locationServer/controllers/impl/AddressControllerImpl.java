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

import br.com.locationServer.controllers.AddressController;
import br.com.locationServer.dtos.AddressDTO;
import br.com.locationServer.exception.AddressException;
import br.com.locationServer.services.IAddressService;

@RestController
@RequestMapping(path = "/address")
public class AddressControllerImpl implements AddressController {

	private IAddressService addressService;

	@Autowired
	public AddressControllerImpl(IAddressService addressService) {
		super();
		this.addressService = addressService;
	}

	@PostMapping("/register")
	@ResponseBody
	@Override
	public ResponseEntity<AddressDTO> registerAddress(@RequestBody AddressDTO addressDTO) {
		try {
			return ResponseEntity.ok(AddressDTO.convertAddressToDto(addressService.registerAddress(addressDTO)));
		} catch (AddressException exception) {
			exception.printStackTrace();
			return ResponseEntity.status(406).body(AddressDTO.createDtoWithMessageError(exception.getMessage()));
		}
	}

	@GetMapping("/search")
	@ResponseBody
	@Override
	public ResponseEntity<List<AddressDTO>> searchAllAddress() {
		try {
			return ResponseEntity.ok(AddressDTO.convertListAddressToListDto(addressService.searchAllAddress()));
		} catch (AddressException exception) {
			exception.printStackTrace();
			return ResponseEntity.status(204).body(Arrays.asList(AddressDTO.createDtoWithMessageError(exception.getMessage())));
		}
	}

	@GetMapping("/internal/search-by/{addressId}")
	@ResponseBody
	@Override
	public ResponseEntity<AddressDTO> searchAddressById(@PathVariable("addressId") Long addressId) {
		try {
			return ResponseEntity.ok(AddressDTO.convertAddressToDto(addressService.searchAddressById(addressId)));
		} catch (AddressException exception) {
			exception.printStackTrace();
			return ResponseEntity.status(204).body(AddressDTO.createDtoWithMessageError(exception.getMessage()));
		}
	}

	@PutMapping("/update")
	@ResponseBody
	@Override
	public ResponseEntity<AddressDTO> updateAddress(@RequestBody AddressDTO addressDTO) {
		try {
			return ResponseEntity.ok(AddressDTO.convertAddressToDto(addressService.updateAddress(addressDTO)));
		} catch (AddressException exception) {
			exception.printStackTrace();
			return ResponseEntity.status(406).body(AddressDTO.createDtoWithMessageError(exception.getMessage()));
		}
	}

	@DeleteMapping("/delete")
	@ResponseBody
	@Override
	public ResponseEntity<AddressDTO> deleteAddress(@RequestBody AddressDTO addressDTO) {
		try {
			addressService.deleteAddress(addressDTO);
			return ResponseEntity.status(200).body(AddressDTO.createDtoWithMessageError(null));
		} catch (AddressException exception) {
			exception.printStackTrace();
			return ResponseEntity.status(406).body(AddressDTO.createDtoWithMessageError(exception.getMessage()));
		}
	}

}