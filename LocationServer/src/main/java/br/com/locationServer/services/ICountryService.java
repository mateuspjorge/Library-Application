package br.com.locationServer.services;

import java.util.List;

import br.com.locationServer.dtos.CountryDTO;
import br.com.locationServer.entitys.Country;
import br.com.locationServer.exceptions.CountryException;

public interface ICountryService {

	public Country registerCountry(CountryDTO countryDTO) throws CountryException;

	public List<Country> searchAllCountries() throws CountryException;

	public Country searchCountryByName(String name) throws CountryException;

	public Country searchCountryById(Long countryId) throws CountryException;

	public Country updateCountry(CountryDTO countryDTO) throws CountryException;

	public void deleteCountry(String name) throws CountryException;

}