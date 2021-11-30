package br.com.locationServer.services;

import java.util.List;

import br.com.locationServer.dtos.CityDTO;
import br.com.locationServer.entitys.City;
import br.com.locationServer.exception.CityException;

public interface ICityService {

	public City registerCity(CityDTO cityDTO) throws CityException;

	public List<City> searchAllCities() throws CityException;

	public City searchCityByName(CityDTO cityDTO) throws CityException;

	public City searchCityById(Long cityId) throws CityException;

	public City updateCity(CityDTO cityDTO) throws CityException;

	public void deleteCity(CityDTO cityDTO) throws CityException;

}