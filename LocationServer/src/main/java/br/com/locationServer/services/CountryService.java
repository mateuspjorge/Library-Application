package br.com.locationServer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.locationServer.repositorys.ICountryRepository;

@Service
public class CountryService {

	private ICountryRepository countryRepository;

	@Autowired
	public CountryService(ICountryRepository countryRepository) {
		super();
		this.countryRepository = countryRepository;
	}

}