package br.com.locationServer.services;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import br.com.locationServer.dtos.CityDTO;
import br.com.locationServer.entitys.City;
import br.com.locationServer.exception.CityException;
import br.com.locationServer.repositorys.ICityRepository;

@Service
public class CityService {

	private ICityRepository cityRepository;

	@Autowired
	public CityService(ICityRepository cityRepository) {
		super();
		this.cityRepository = cityRepository;
	}

	public City cityRegister(CityDTO cityDTO) throws CityException {
		validateCity(cityDTO);
		return cityRepository.save(City.builder()
							 .stateId(cityDTO.getStateId())
							 .name(cityDTO.getName())
							 .build());
	}

	public List<City> searchAllCities() throws CityException {
		List<City> citiesFound = cityRepository.findAll();
		if (CollectionUtils.isEmpty(citiesFound)) {
			throw new CityException("Nenhum registro foi encontrado.");
		}
		return citiesFound;
	}

	public City searchCityByStateAndName(CityDTO cityDTO) throws CityException {
		validateCity(cityDTO);
		return cityRepository.findByStateIdAndName(cityDTO.getStateId(), cityDTO.getName());
	}

	public City updateCity(CityDTO cityDTO) throws CityException {
		City cityFound = searchCityByStateAndName(cityDTO);
		if (ObjectUtils.isEmpty(cityFound)) {
			throw new CityException("Nenhum registro foi encontrado.");
		}
		cityFound.setStateId(cityDTO.getNewStateId());
		cityFound.setName(cityDTO.getNewName());
		return cityRepository.save(cityFound);
	}

	public void deleteCity(CityDTO cityDTO) throws CityException {
		City cityFound = searchCityByStateAndName(cityDTO);
		if (ObjectUtils.isEmpty(cityFound)) {
			throw new CityException("Nenhum registro foi encontrado.");
		}
		cityRepository.delete(cityFound);
	}

	protected void validateCity(CityDTO cityDTO) throws CityException {
		if (ObjectUtils.isEmpty(cityDTO.getStateId())) {
			throw new CityException("O Estado informado é inválido.");
		}
		if (StringUtils.isEmpty(cityDTO.getName())) {
			throw new CityException("O nome informado é inválido.");
		}
	}

}