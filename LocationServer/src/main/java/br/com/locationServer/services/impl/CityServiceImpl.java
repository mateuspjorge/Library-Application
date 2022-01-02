package br.com.locationServer.services.impl;

import java.util.List;
import java.util.Objects;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import br.com.locationServer.dtos.CityDTO;
import br.com.locationServer.dtos.StateDTO;
import br.com.locationServer.entitys.City;
import br.com.locationServer.exceptions.CityException;
import br.com.locationServer.repositorys.ICityRepository;
import br.com.locationServer.services.ICityService;

@Service
public class CityServiceImpl implements ICityService {

	private static final String MSG_ERROR_NENHUM_REGISTRO_ENCONTRADO = "Nenhum registro foi encontrado.";

	private ICityRepository cityRepository;

	@Autowired
	public CityServiceImpl(ICityRepository cityRepository) {
		super();
		this.cityRepository = cityRepository;
	}

	@Override
	public City registerCity(CityDTO cityDTO) throws CityException {
		validateCity(cityDTO);
		return cityRepository.save(CityDTO.convertDtoToCity(cityDTO));
	}

	@Override
	public List<City> searchAllCities() throws CityException {
		List<City> citiesFound = cityRepository.findAll();
		if (CollectionUtils.isEmpty(citiesFound)) {
			throw new CityException(MSG_ERROR_NENHUM_REGISTRO_ENCONTRADO);
		}
		return citiesFound;
	}

	@Override
	public City searchCityByName(CityDTO cityDTO) throws CityException {
		validateCity(cityDTO);
		City cityFound = cityRepository.findByName(cityDTO.getName().toUpperCase());
		if (Objects.isNull(cityFound)) {
			throw new CityException(MSG_ERROR_NENHUM_REGISTRO_ENCONTRADO);
		}
		return cityFound;
	}

	@Override
	public City searchCityById(Long cityId) throws CityException {
		City cityFound = cityRepository.findById(cityId).orElse(null);
		if (Objects.isNull(cityFound)) {
			throw new CityException(MSG_ERROR_NENHUM_REGISTRO_ENCONTRADO);
		}
		return cityFound;
	}

	@Override
	public City updateCity(CityDTO cityDTO) throws CityException {
		City cityFound = searchCityByName(cityDTO);
		if (Objects.nonNull(cityDTO.getState())) {
			cityFound.setState(StateDTO.convertDtoToState(cityDTO.getNewState()));
		}
		if (StringUtils.isBlank(cityDTO.getName())) {
			cityFound.setName(cityDTO.getNewName());
		}
		return cityRepository.save(cityFound);
	}

	@Override
	public void deleteCity(CityDTO cityDTO) throws CityException {
		cityRepository.delete(searchCityByName(cityDTO));
	}

	protected void validateCity(CityDTO cityDTO) throws CityException {
		if (Objects.isNull(cityDTO.getState())) {
			throw new CityException("O Estado informado é inválido.");
		}
		if (StringUtils.isBlank(cityDTO.getName())) {
			throw new CityException("O nome informado é inválido.");
		}
	}

}