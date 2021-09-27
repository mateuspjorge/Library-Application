package br.com.locationServer.services;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

	public List<Country> searchAllCountries() {
		return countryRepository.findAll();
	}

	public Country searchByName(String name) throws CountryException {
		if (StringUtils.isEmpty(name)) {
			throw new CountryException("O nome digitado é inválido.");
		}
		return countryRepository.findByName(name);
	}

	public Country updateCountry(CountryDTO countryDTO) throws CountryException {
		Country countryFound = searchByName(countryDTO.getName());
		if (ObjectUtils.isEmpty(countryFound)) {
			throw new CountryException("Nenhum registro foi encontrado.");
		}
		countryFound.setName(countryDTO.getNewName());
		return countryRepository.save(countryFound);
	}

	public Boolean deleteCountry(Long idCountry) throws CountryException {
		Country country = countryRepository.findById(idCountry).orElse(null);
		if (ObjectUtils.isEmpty(country)) {
			throw new CountryException("Nenhum registro foi encontrado.");
		}
		countryRepository.delete(country);
		return Boolean.TRUE;
	}

}