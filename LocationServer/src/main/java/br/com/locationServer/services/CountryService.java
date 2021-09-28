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

	private ICountryRepository countryRepository;

	@Autowired
	public CountryService(ICountryRepository countryRepository) {
		super();
		this.countryRepository = countryRepository;
	}

	public Country countryRegister(String name) throws CountryException {
		if (StringUtils.isEmpty(name)) {
			throw new CountryException("O nome digitado é inválido");
		}
		return countryRepository.save(Country.builder().name(name).build());
	}

	public List<Country> searchAllCountries() throws CountryException {
		List<Country> countriesFound = countryRepository.findAll();
		if (CollectionUtils.isEmpty(countriesFound)) {
			throw new CountryException("Nenhum registro foi encontrado.");
		}
		return countriesFound;
	}

	public Country searchCountryByName(String name) throws CountryException {
		if (StringUtils.isEmpty(name)) {
			throw new CountryException("O nome digitado é inválido.");
		}
		return countryRepository.findByName(name);
	}

	public Country updateCountry(CountryDTO countryDTO) throws CountryException {
		Country countryFound = searchCountryByName(countryDTO.getName());
		if (ObjectUtils.isEmpty(countryFound)) {
			throw new CountryException("Nenhum registro foi encontrado.");
		}
		countryFound.setName(countryDTO.getNewName());
		return countryRepository.save(countryFound);
	}

	public void deleteCountry(String name) throws CountryException {
		Country countryFound = searchCountryByName(name);
		if (ObjectUtils.isEmpty(countryFound)) {
			throw new CountryException("Nenhum registro foi encontrado.");
		}
		countryRepository.delete(countryFound);
	}

}