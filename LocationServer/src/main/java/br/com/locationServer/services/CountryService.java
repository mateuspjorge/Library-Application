package br.com.locationServer.services;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import br.com.locationServer.dtos.CountryDTO;
import br.com.locationServer.entitys.Country;
import br.com.locationServer.exception.CountryException;
import br.com.locationServer.repositorys.ICountryRepository;

@Service
public class CountryService {

	private static final String MSG_ERROR_NENHUM_REGISTRO_ENCONTRADO = "Nenhum registro foi encontrado.";

	private ICountryRepository countryRepository;

	@Autowired
	public CountryService(ICountryRepository countryRepository) {
		super();
		this.countryRepository = countryRepository;
	}

	public Country registerCountry(CountryDTO countryDTO) throws CountryException {
		validateCountry(countryDTO.getName());
		return countryRepository.save(Country.builder().name(countryDTO.getName()).build());
	}

	public List<Country> searchAllCountries() throws CountryException {
		List<Country> countriesFound = countryRepository.findAll();
		if (CollectionUtils.isEmpty(countriesFound)) {
			throw new CountryException(MSG_ERROR_NENHUM_REGISTRO_ENCONTRADO);
		}
		return countriesFound;
	}

	public Country searchCountryByName(String name) throws CountryException {
		validateCountry(name);
		Country countryFound = countryRepository.findByName(name);
		if (ObjectUtils.isEmpty(countryFound)) {
			throw new CountryException(MSG_ERROR_NENHUM_REGISTRO_ENCONTRADO);
		}
		return countryFound;
	}

	public Country updateCountry(CountryDTO countryDTO) throws CountryException {
		Country countryFound = searchCountryByName(countryDTO.getName());
		countryFound.setName(countryDTO.getNewName());
		return countryRepository.save(countryFound);
	}

	public void deleteCountry(String name) throws CountryException {
		countryRepository.delete(searchCountryByName(name));
	}

	public Country searchCountryById(Long countryId) throws CountryException {
		Country countryFound = countryRepository.findById(countryId).orElse(null);
		if (ObjectUtils.isEmpty(countryFound)) {
			throw new CountryException(MSG_ERROR_NENHUM_REGISTRO_ENCONTRADO);
		}
		return countryFound;
	}

	protected void validateCountry(String name) throws CountryException {
		if (StringUtils.isEmpty(name)) {
			throw new CountryException("O nome informado é inválido.");
		}
	}

}