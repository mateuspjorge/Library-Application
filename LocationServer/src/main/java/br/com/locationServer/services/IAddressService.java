package br.com.locationServer.services;

import java.util.List;

import br.com.locationServer.dtos.AddressDTO;
import br.com.locationServer.entitys.Address;
import br.com.locationServer.exceptions.AddressException;

public interface IAddressService {

	public Address registerAddress(AddressDTO addressDTO) throws AddressException;

	public List<Address> searchAllAddress() throws AddressException;

	public Address searchAddressById(Long addressId) throws AddressException;

	public Address updateAddress(AddressDTO addressDTO) throws AddressException;

	public void deleteAddress(AddressDTO addressDTO) throws AddressException;

}