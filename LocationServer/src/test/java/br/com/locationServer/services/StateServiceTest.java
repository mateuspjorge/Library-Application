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

import br.com.locationServer.dtos.StateDTO;
import br.com.locationServer.entitys.State;
import br.com.locationServer.exception.StateException;
import br.com.locationServer.repositorys.IStateRepository;

@SpringBootTest
public class StateServiceTest {

	@Mock
	private IStateRepository stateRepository;

	@InjectMocks
	private StateService stateService;

	@SuppressWarnings("deprecation")
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldRegisterStateSuccessfully() throws StateException {
		when(stateRepository.save(any())).thenReturn(createState(1L, 1L, "Paraíba", "PB"));
		Assert.assertNotNull(stateService.registerState(createStateDto(1L, "Paraíba", "PB")));
	}

	@Test
	public void shouldThrowErrorOfRegisterStateIfCountryIdFieldAreMissing() throws StateException {
		when(stateRepository.save(any())).thenReturn(createState(1L, 1L, "Paraíba", "PB"));
		Assert.assertThrows(StateException.class, () -> stateService.registerState(createStateDto(null, "Paraíba", "PB")));
	}

	@Test
	public void shouldThrowErrorOfRegisterStateIfNameFieldAreMissing() throws StateException {
		when(stateRepository.save(any())).thenReturn(createState(1L, 1L, "Paraíba", "PB"));
		Assert.assertThrows(StateException.class, () -> stateService.registerState(createStateDto(1L, null, "PB")));
	}

	@Test
	public void shouldThrowErrorOfRegisterStateIfInitialsFieldAreMissing() throws StateException {
		when(stateRepository.save(any())).thenReturn(createState(1L, 1L, "Paraíba", "PB"));
		Assert.assertThrows(StateException.class, () -> stateService.registerState(createStateDto(1L, "Paraíba", null)));
	}

	private State createState(Long id, Long countryId, String name, String inicials) {
		return State.builder()
				    .id(id)
				    .countryId(countryId)
				    .name(name)
				    .initials(inicials)
				    .build();
	}

	private StateDTO createStateDto(Long countryId, String name, String inicials) {
		return StateDTO.builder()
					   .countryId(countryId)
					   .name(name)
					   .initials(inicials)
					   .build();
	}

}