package br.com.locationServer.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.locationServer.dtos.CityDTO;
import br.com.locationServer.entitys.City;
import br.com.locationServer.exception.CityException;
import br.com.locationServer.repositorys.ICityRepository;
import br.com.locationServer.services.impl.CityServiceImpl;

@SpringBootTest
public class CityServiceTest {

	@Mock
	private ICityRepository cityRepository;

	@InjectMocks
	private CityServiceImpl cityService;

	@SuppressWarnings("deprecation")
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldRegisterCitySuccessfully() throws CityException {
		when(cityRepository.save(any())).thenReturn(createCity(1L, 1L, "João Pessoa"));
		Assert.assertNotNull(cityService.registerCity(createCityDto(1L, "João Pessoa")));
	}

	@Test
	public void shouldThrowErrorOfRegisterCityIfStateIdFieldAreMissing() throws CityException {
		when(cityRepository.save(any())).thenReturn(createCity(1L, 1L, "João Pessoa"));
		Assert.assertThrows(CityException.class, () -> cityService.registerCity(createCityDto(null, "João Pessoa")));
	}

	@Test
	public void shouldThrowErrorOfRegisterCityIfNameFieldAreMissing() throws CityException {
		when(cityRepository.save(any())).thenReturn(createCity(1L, 1L, "João Pessoa"));
		Assert.assertThrows(CityException.class, () -> cityService.registerCity(createCityDto(1L, null)));
	}

	@Test
	public void shouldListAllCitySuccessfully() throws CityException {
		when(cityRepository.findAll()).thenReturn(Arrays.asList(createCity(1L, 1L, "João Pessoa")));
		Assert.assertEquals(1, cityService.searchAllCities().size());
	}

	@Test
	public void shouldThrowErrorOfCityListEmpty() throws CityException {
		when(cityRepository.findAll()).thenReturn(Arrays.asList());
		Assert.assertThrows(CityException.class, () -> cityService.searchAllCities());
	}

	@Test
	public void shouldUpdatedCitySuccessfully() throws CityException {
		when(cityRepository.findByStateIdAndName(any(), any())).thenReturn(createCity(1L, 1L, "João Pessoa"));
		when(cityRepository.save(any())).thenReturn(createCity(1L, 1L, "João Pessoa"));
		Assert.assertNotNull(cityService.updateCity(createCityDto(1L, "Patos")));
	}

	@Test
	public void shouldThrowErrorOfUpdatedCityIfStateIdFieldAreMissing() throws CityException {
		when(cityRepository.save(any())).thenReturn(createCity(1L, 1L, "João Pessoa"));
		Assert.assertThrows(CityException.class, () -> cityService.updateCity(createCityDto(null, "Patos")));
	}

	@Test
	public void shouldThrowErrorOfUpdatedCityIfNameFieldAreMissing() throws CityException {
		when(cityRepository.save(any())).thenReturn(createCity(1L, 1L, "João Pessoa"));
		Assert.assertThrows(CityException.class, () -> cityService.updateCity(createCityDto(1L, null)));
	}

	@Test
	public void shouldThrowErrorOfUpdatedCityIfCityNotFound() throws CityException {
		when(cityRepository.findByStateIdAndName(any(), any())).thenReturn(null);
		Assert.assertThrows(CityException.class, () -> cityService.updateCity(createCityDto(1L, "Patos")));
	}

	@Test
	public void shouldDeleteCitySuccessfully() throws CityException {
		when(cityRepository.findByStateIdAndName(any(), any())).thenReturn(createCity(1L, 1L, "João Pessoa"));
		cityService.deleteCity(createCityDto(1L, "João Pessoa"));
	}

	@Test
	public void shouldThrowErrorOfDeleteCityIfStateIdFieldAreMissing() throws CityException {
		Assert.assertThrows(CityException.class, () -> cityService.deleteCity(createCityDto(null, "João Pessoa")));
	}

	@Test
	public void shouldThrowErrorOfDeleteCityIfNameFieldAreMissing() throws CityException {
		Assert.assertThrows(CityException.class, () -> cityService.deleteCity(createCityDto(1L, null)));
	}

	@Test
	public void shouldThrowErrorOfDeleteStateIfStateNotFound() throws CityException {
		when(cityRepository.findByStateIdAndName(any(), any())).thenReturn(null);
		Assert.assertThrows(CityException.class, () -> cityService.deleteCity(createCityDto(1L, "Patos")));
	}

	@Test
	public void shouldFoundCitySuccessfully() throws CityException {
		when(cityRepository.findById(any())).thenReturn(Optional.of(createCity(1L, 1L, "João Pessoa")));
		Assert.assertNotNull(cityService.searchCityById(1L));
	}

	@Test
	public void shouldThrowErrorOfSearchCityByIdIfIdFieldNotFound() throws CityException {
		when(cityRepository.findById(any())).thenReturn(Optional.empty());
		Assert.assertThrows(CityException.class, () -> cityService.searchCityById(1L));
	}

	private City createCity(Long id, Long stateId, String name) {
		return City.builder().id(id).stateId(stateId).name(name).build();
	}

	private CityDTO createCityDto(Long stateId, String name) {
		return CityDTO.builder().stateId(stateId).name(name).build();
	}

}