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

import br.com.locationServer.dtos.CountryDTO;
import br.com.locationServer.entitys.Country;
import br.com.locationServer.exception.CountryException;
import br.com.locationServer.repositorys.ICountryRepository;
import br.com.locationServer.services.impl.CountryServiceImpl;

@SpringBootTest
public class CountryServiceTest {

	@Mock
	private ICountryRepository countryRepository;

	@InjectMocks
	private CountryServiceImpl countryService;

	@SuppressWarnings("deprecation")
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldRegisterCountrySuccessfully() throws CountryException {
		when(countryRepository.save(any())).thenReturn(createCountry(1L, "Brasil"));
		Assert.assertNotNull(countryService.registerCountry(createCountryDto("Brasil", null)));
	}

	@Test
	public void shouldThrowErrorOfRegisterCountryIfNameFieldAreMissing() throws CountryException {
		when(countryRepository.save(any())).thenReturn(createCountry(1L, "Brasil"));
		Assert.assertThrows(CountryException.class, () -> countryService.registerCountry(createCountryDto(null, null)));
	}

	@Test
	public void shouldListAllCountriesSuccessfully() throws CountryException {
		when(countryRepository.findAll()).thenReturn(Arrays.asList(createCountry(1L, "Brasil")));
		Assert.assertEquals(1, countryService.searchAllCountries().size());
	}

	@Test
	public void shouldThrowErrorOfCountryListEmpty() throws CountryException {
		when(countryRepository.findAll()).thenReturn(Arrays.asList());
		Assert.assertThrows(CountryException.class, () -> countryService.searchAllCountries());
	}

	@Test
	public void shouldUpdatedCountrySuccessfully() throws CountryException {
		when(countryRepository.findByName(any())).thenReturn(createCountry(1L, "Brasil"));
		when(countryRepository.save(any())).thenReturn(createCountry(1L, "EUA"));
		Assert.assertNotNull(countryService.updateCountry(createCountryDto("Brasil", "EUA")));
	}

	@Test
	public void shouldThrowErrorOfUpdateCountryIfNameFieldAreMissing() throws CountryException {
		when(countryRepository.save(any())).thenReturn(createCountry(1L, "Brasil"));
		Assert.assertThrows(CountryException.class, () -> countryService.updateCountry(createCountryDto(null, null)));
	}

	@Test
	public void shouldThrowErrorOfUpdateCountryIfCountryNameNotFound() throws CountryException {
		when(countryRepository.findByName(any())).thenReturn(null);
		Assert.assertThrows(CountryException.class,
				() -> countryService.updateCountry(createCountryDto("Brasil", "EUA")));
	}

	@Test
	public void shouldDeleteCountrySuccessfully() throws CountryException {
		when(countryRepository.findByName(any())).thenReturn(createCountry(1L, "Brasil"));
		countryService.deleteCountry("Brasil");
	}

	@Test
	public void shouldThrowErrorOfDeleteCountryIfNameFieldAreMissing() throws CountryException {
		Assert.assertThrows(CountryException.class, () -> countryService.deleteCountry(null));
	}

	@Test
	public void shouldThrowErrorOfDeleteCountryIfCountryNameNotFound() throws CountryException {
		when(countryRepository.findByName(any())).thenReturn(null);
		Assert.assertThrows(CountryException.class, () -> countryService.deleteCountry("Brasil"));
	}

	@Test
	public void shouldFoundCountrySuccessfully() throws CountryException {
		when(countryRepository.findById(any())).thenReturn(Optional.of(createCountry(1L, "Brasil")));
		Assert.assertNotNull(countryService.searchCountryById(1L));
	}

	@Test
	public void shouldThrowErrorOfSearchCountryByIdIfIdFieldNotFound() throws CountryException {
		when(countryRepository.findById(any())).thenReturn(Optional.empty());
		Assert.assertThrows(CountryException.class, () -> countryService.searchCountryById(1L));
	}

	private Country createCountry(Long id, String name) {
		return Country.builder().id(id).name(name).build();
	}

	private CountryDTO createCountryDto(String name, String newName) {
		return CountryDTO.builder().name(name).newName(newName).build();
	}

}