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

	@GetMapping("/search")
	@ResponseBody
	public ResponseEntity<List<AddressDTO>> searchAllAddress() throws AddressException {
		List<AddressDTO> addressFound = AddressDTO.convertListAddressToListDto(addressService.searchAllAddress());
		addressFound.forEach(addressDto -> {
			try {
				addressDto.setCity(iCityFeign.searchCityById(addressDto.getCityId()).getBody());
			} catch (CityException | StateException e) {
				e.printStackTrace();
			}
		});
		return new ResponseEntity<>(addressFound, HttpStatus.ACCEPTED);
	}

	@GetMapping("/internal/search-by/{addressId}")
	@ResponseBody
	public ResponseEntity<AddressDTO> searchAddressById(@PathVariable("addressId") Long addressId) throws AddressException, CityException, StateException {
		AddressDTO addressFound = AddressDTO.convertAddressToDto(addressService.searchAddressById(addressId));
		addressFound.setCity(iCityFeign.searchCityById(addressFound.getCityId()).getBody());
		return new ResponseEntity<>(addressFound, HttpStatus.ACCEPTED);
	}

	@PutMapping("/update")
	@ResponseBody
	public ResponseEntity<AddressDTO> updateAddress(@RequestBody AddressDTO addressDTO) throws AddressException, CityException, StateException {
		AddressDTO updatedAddress = AddressDTO.convertAddressToDto(addressService.updateAddress(addressDTO));
		updatedAddress.setCity(iCityFeign.searchCityById(updatedAddress.getCityId()).getBody());
		return new ResponseEntity<>(updatedAddress, HttpStatus.CREATED);
	}

	@DeleteMapping("/delete")
	@ResponseBody
	public void deleteAddress(@RequestBody AddressDTO addressDTO) throws AddressException {
		addressService.deleteAddress(addressDTO);
	}

}