package br.com.locationServer.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

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

@SpringBootTest
public class CountryServiceTest {

	@Mock
	private ICountryRepository countryRepository;

	@InjectMocks
	private CountryService countryService;

	@SuppressWarnings("deprecation")
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldRegisterCountrySuccessfully() throws CountryException {
		when(countryRepository.save(any())).thenReturn(Country.builder().id(1L).name("Brasil").build());
		Assert.assertNotNull(countryService.registerCountry(CountryDTO.builder().name("Brasil").build()));
	}

}