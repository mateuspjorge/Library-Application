package br.com.locationServer.services;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import br.com.locationServer.dtos.AddressDTO;
import br.com.locationServer.entitys.Address;
import br.com.locationServer.exception.AddressException;
import br.com.locationServer.repositorys.IAddressRepository;

@Service
public class AddressService {

	private static final String MSG_ERROR_NENHUM_REGISTRO_ENCONTRADO = "Nenhum registro foi encontrado.";

	private IAddressRepository addressRepository;

	@Autowired
	public AddressService(IAddressRepository addressRepository) {
		super();
		this.addressRepository = addressRepository;
	}

	public Address registerAddress(AddressDTO addressDTO) throws AddressException {
		validateAddress(addressDTO);
		return addressRepository.save(Address.builder()
											 .cityId(addressDTO.getCityId())
											 .number(addressDTO.getNumber())
											 .cep(addressDTO.getCep())
											 .street(addressDTO.getStreet())
											 .district(addressDTO.getDistrict())
											 .complement(addressDTO.getComplement())
											 .build());
	}

	public List<Address> searchAllAddress() throws AddressException {
		List<Address> addressFound = addressRepository.findAll();
		if (CollectionUtils.isEmpty(addressFound)) {
			throw new AddressException(MSG_ERROR_NENHUM_REGISTRO_ENCONTRADO);
		}
		return addressFound;
	}

	public Address updateAddress(AddressDTO addressDTO) throws AddressException {
		validateAddress(addressDTO);
		Address addressFound = addressRepository.findById(addressDTO.getId()).orElse(null);
		if (ObjectUtils.isEmpty(addressFound)) {
			throw new AddressException(MSG_ERROR_NENHUM_REGISTRO_ENCONTRADO);
		}
		addressFound.setCityId(addressDTO.getNewCityId());
		addressFound.setNumber(addressDTO.getNewNumber());
		addressFound.setCep(addressDTO.getNewCep());
		addressFound.setStreet(addressDTO.getNewStreet());
		addressFound.setDistrict(addressDTO.getNewDistrict());
		addressFound.setComplement(addressDTO.getNewComplement());
		return addressRepository.save(addressFound);
	}

	public void deleteAddress(AddressDTO addressDTO) throws AddressException {
		Address addressFound = addressRepository.findById(addressDTO.getId()).orElse(null);
		if (ObjectUtils.isEmpty(addressFound)) {
			throw new AddressException(MSG_ERROR_NENHUM_REGISTRO_ENCONTRADO);
		}
		addressRepository.delete(addressFound);
	}

	public Address searchAddressById(Long addressId) throws AddressException {
		Address addressFound = addressRepository.findById(addressId).orElse(null);
		if (ObjectUtils.isEmpty(addressFound)) {
			throw new AddressException(MSG_ERROR_NENHUM_REGISTRO_ENCONTRADO);
		}
		return addressFound;
	}

	protected void validateAddress(AddressDTO addressDTO) throws AddressException {
		if (ObjectUtils.isEmpty(addressDTO.getCityId())) {
			throw new AddressException("A Cidade informada é inválida.");
		}
		if (ObjectUtils.isEmpty(addressDTO.getNumber())) {
			throw new AddressException("O número informado é inválido.");
		}
		if (StringUtils.isEmpty(addressDTO.getCep())) {
			throw new AddressException("O cep informado é inválido.");
		}
		if (StringUtils.isEmpty(addressDTO.getStreet())) {
			throw new AddressException("A rua informada é inválida.");
		}
		if (StringUtils.isEmpty(addressDTO.getDistrict())) {
			throw new AddressException("O bairro informado é inválido.");
		}
	}

}