package br.com.locationServer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.locationServer.repositorys.IAddressRepository;

@Service
public class AddressService {

	private IAddressRepository addressRepository;

	@Autowired
	public AddressService(IAddressRepository addressRepository) {
		super();
		this.addressRepository = addressRepository;
	}

}