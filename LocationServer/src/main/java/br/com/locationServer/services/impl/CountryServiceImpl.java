package br.com.locationServer.services.impl;

import java.util.List;
import java.util.Objects;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import br.com.locationServer.dtos.CountryDTO;
import br.com.locationServer.entitys.Country;
import br.com.locationServer.exceptions.CountryException;
import br.com.locationServer.repositorys.ICountryRepository;
import br.com.locationServer.services.ICountryService;

@Service
public class CountryServiceImpl implements ICountryService {

	private static final String MSG_ERROR_NENHUM_REGISTRO_ENCONTRADO = "Nenhum registro foi encontrado.";

	private ICountryRepository countryRepository;

	@Autowired
	public CountryServiceImpl(ICountryRepository countryRepository) {
		super();
		this.countryRepository = countryRepository;
	}

	@Override
	public Country registerCountry(CountryDTO countryDTO) throws CountryException {
		validateCountry(countryDTO.getName());
		return countryRepository.save(CountryDTO.convertDtoToCountry(countryDTO));
	}

	@Override
	public List<Country> searchAllCountries() throws CountryException {
		List<Country> countriesFound = countryRepository.findAll();
		if (CollectionUtils.isEmpty(countriesFound)) {
			throw new CountryException(MSG_ERROR_NENHUM_REGISTRO_ENCONTRADO);
		}
		return countriesFound;
	}

	@Override
	public Country searchCountryByName(String name) throws CountryException {
		validateCountry(name);
		Country countryFound = countryRepository.findByName(name.toUpperCase());
		if (Objects.isNull(countryFound)) {
			throw new CountryException(MSG_ERROR_NENHUM_REGISTRO_ENCONTRADO);
		}
		return countryFound;
	}

	@Override
	public Country searchCountryById(Long countryId) throws CountryException {
		Country countryFound = countryRepository.findById(countryId).orElse(null);
		if (Objects.isNull(countryFound)) {
			throw new CountryException(MSG_ERROR_NENHUM_REGISTRO_ENCONTRADO);
		}
		return countryFound;
	}

	@Override
	public Country updateCountry(CountryDTO countryDTO) throws CountryException {
		Country countryFound = searchCountryByName(countryDTO.getName());
		if (StringUtils.isNotBlank(countryDTO.getNewName())) {
			countryFound.setName(countryDTO.getNewName());
		}
		return countryRepository.save(countryFound);
	}

	@Override
	public void deleteCountry(String name) throws CountryException {
		countryRepository.delete(searchCountryByName(name));
	}

	protected void validateCountry(String name) throws CountryException {
		if (StringUtils.isBlank(name)) {
			throw new CountryException("O nome informado é inválido.");
		}
	}

}