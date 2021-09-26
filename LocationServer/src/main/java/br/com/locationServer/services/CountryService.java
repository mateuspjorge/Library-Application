package br.com.locationServer.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import br.com.locationServer.dtos.CountryDTO;
import br.com.locationServer.entitys.Country;
import br.com.locationServer.repositorys.ICountryRepository;

@Service
public class CountryService {

	private ICountryRepository countryRepository;

	@Autowired
	public CountryService(ICountryRepository countryRepository) {
		super();
		this.countryRepository = countryRepository;
	}

	public Country countryRegister(String name) {
		return countryRepository.save(Country.builder().name(name).build());
	}

	public List<Country> searchAllCountries() {
		return countryRepository.findAll();
	}

	public Country searchByName(String name) {
		return countryRepository.findByName(name);
	}

	public Country updateCountry(CountryDTO countryDTO) {
		Country countryFound = searchByName(countryDTO.getName());
		if (ObjectUtils.isEmpty(countryFound)) {
			return null;
		}
		countryFound.setName(countryDTO.getNewName());
		return countryRepository.save(countryFound);
	}

}