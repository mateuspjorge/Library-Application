package br.com.locationServer.services.impl;

import java.util.List;
import java.util.Objects;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import br.com.locationServer.dtos.AddressDTO;
import br.com.locationServer.dtos.CityDTO;
import br.com.locationServer.entitys.Address;
import br.com.locationServer.exception.AddressException;
import br.com.locationServer.repositorys.IAddressRepository;
import br.com.locationServer.services.IAddressService;

@Service
public class AddressServiceImpl implements IAddressService {

	private static final String MSG_ERROR_NENHUM_REGISTRO_ENCONTRADO = "Nenhum registro foi encontrado.";

	private IAddressRepository addressRepository;

	@Autowired
	public AddressServiceImpl(IAddressRepository addressRepository) {
		super();
		this.addressRepository = addressRepository;
	}

	@Override
	public Address registerAddress(AddressDTO addressDTO) throws AddressException {
		validateAddress(addressDTO);
		return addressRepository.save(AddressDTO.convertDtoToAddress(addressDTO));
	}

	@Override
	public List<Address> searchAllAddress() throws AddressException {
		List<Address> addressFound = addressRepository.findAll();
		if (CollectionUtils.isEmpty(addressFound)) {
			throw new AddressException(MSG_ERROR_NENHUM_REGISTRO_ENCONTRADO);
		}
		return addressFound;
	}

	@Override
	public Address updateAddress(AddressDTO addressDTO) throws AddressException {
		validateAddress(addressDTO);
		Address addressFound = searchAddressById(addressDTO.getId());
		if (Objects.isNull(addressDTO.getCity())) {
			addressFound.setCity(CityDTO.convertDtoToCity(addressDTO.getNewCity()));
		}
		if (Objects.isNull(addressDTO.getNumber())) {
			addressFound.setNumber(addressDTO.getNewNumber());
		}
		if (StringUtils.isBlank(addressDTO.getCep())) {
			addressFound.setCep(addressDTO.getNewCep());
		}
		if (StringUtils.isBlank(addressDTO.getStreet())) {
			addressFound.setStreet(addressDTO.getNewStreet());
		}
		if (StringUtils.isBlank(addressDTO.getDistrict())) {
			addressFound.setDistrict(addressDTO.getNewDistrict());
		}
		if (StringUtils.isBlank(addressDTO.getComplement())) {
			addressFound.setComplement(addressDTO.getNewComplement());
		}
		return addressRepository.save(addressFound);
	}

	@Override
	public void deleteAddress(AddressDTO addressDTO) throws AddressException {
		Address addressFound = addressRepository.findById(addressDTO.getId()).orElse(null);
		if (Objects.isNull(addressFound)) {
			throw new AddressException(MSG_ERROR_NENHUM_REGISTRO_ENCONTRADO);
		}
		addressRepository.delete(addressFound);
	}

	@Override
	public Address searchAddressById(Long addressId) throws AddressException {
		Address addressFound = addressRepository.findById(addressId).orElse(null);
		if (Objects.isNull(addressFound)) {
			throw new AddressException(MSG_ERROR_NENHUM_REGISTRO_ENCONTRADO);
		}
		return addressFound;
	}

	protected void validateAddress(AddressDTO addressDTO) throws AddressException {
		if (Objects.isNull(addressDTO.getCity())) {
			throw new AddressException("A Cidade informada é inválida.");
		}
		if (Objects.isNull(addressDTO.getNumber())) {
			throw new AddressException("O número informado é inválido.");
		}
		if (StringUtils.isBlank(addressDTO.getCep())) {
			throw new AddressException("O cep informado é inválido.");
		}
		if (StringUtils.isBlank(addressDTO.getStreet())) {
			throw new AddressException("A rua informada é inválida.");
		}
		if (StringUtils.isBlank(addressDTO.getDistrict())) {
			throw new AddressException("O bairro informado é inválido.");
		}
	}

}