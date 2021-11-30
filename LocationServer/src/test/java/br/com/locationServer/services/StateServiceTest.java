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

import br.com.locationServer.dtos.StateDTO;
import br.com.locationServer.entitys.State;
import br.com.locationServer.exception.StateException;
import br.com.locationServer.repositorys.IStateRepository;
import br.com.locationServer.services.impl.StateServiceImpl;

@SpringBootTest
public class StateServiceTest {

	@Mock
	private IStateRepository stateRepository;

	@InjectMocks
	private StateServiceImpl stateService;

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
		Assert.assertThrows(StateException.class,
				() -> stateService.registerState(createStateDto(null, "Paraíba", "PB")));
	}

	@Test
	public void shouldThrowErrorOfRegisterStateIfNameFieldAreMissing() throws StateException {
		when(stateRepository.save(any())).thenReturn(createState(1L, 1L, "Paraíba", "PB"));
		Assert.assertThrows(StateException.class, () -> stateService.registerState(createStateDto(1L, null, "PB")));
	}

	@Test
	public void shouldThrowErrorOfRegisterStateIfInitialsFieldAreMissing() throws StateException {
		when(stateRepository.save(any())).thenReturn(createState(1L, 1L, "Paraíba", "PB"));
		Assert.assertThrows(StateException.class,
				() -> stateService.registerState(createStateDto(1L, "Paraíba", null)));
	}

	@Test
	public void shouldListAllStatesSuccessfully() throws StateException {
		when(stateRepository.findAll()).thenReturn(Arrays.asList(createState(1L, 1L, "Paraíba", "PB")));
		Assert.assertEquals(1, stateService.searchAllStates().size());
	}

	@Test
	public void shouldThrowErrorOfStateListEmpty() throws StateException {
		when(stateRepository.findAll()).thenReturn(Arrays.asList());
		Assert.assertThrows(StateException.class, () -> stateService.searchAllStates());
	}

	@Test
	public void shouldUpdatedStateSuccessfully() throws StateException {
		when(stateRepository.findByCountryIdAndNameAndInitials(any(), any(), any()))
				.thenReturn(createState(1L, 1L, "Paraíba", "PB"));
		when(stateRepository.save(any())).thenReturn(createState(1L, 1L, "Paraíba", "PB"));
		Assert.assertNotNull(stateService.updateState(createStateDto(1L, "Paraíba", "PB")));
	}

	@Test
	public void shouldThrowErrorOfUpdatedStateIfCountryIdFieldAreMissing() throws StateException {
		when(stateRepository.save(any())).thenReturn(createState(1L, 1L, "Paraíba", "PB"));
		Assert.assertThrows(StateException.class,
				() -> stateService.updateState(createStateDto(null, "Paraíba", "PB")));
	}

	@Test
	public void shouldThrowErrorOfUpdatedStateIfNameFieldAreMissing() throws StateException {
		when(stateRepository.save(any())).thenReturn(createState(1L, 1L, "Paraíba", "PB"));
		Assert.assertThrows(StateException.class, () -> stateService.updateState(createStateDto(1L, null, "PB")));
	}

	@Test
	public void shouldThrowErrorOfUpdatedStateIfInitialsFieldAreMissing() throws StateException {
		when(stateRepository.save(any())).thenReturn(createState(1L, 1L, "Paraíba", "PB"));
		Assert.assertThrows(StateException.class, () -> stateService.updateState(createStateDto(1L, "Paraíba", null)));
	}

	@Test
	public void shouldThrowErrorOfUpdatedStateIfStateNotFound() throws StateException {
		when(stateRepository.findByCountryIdAndNameAndInitials(any(), any(), any())).thenReturn(null);
		Assert.assertThrows(StateException.class, () -> stateService.updateState(createStateDto(1L, "Paraíba", "PB")));
	}

	@Test
	public void shouldDeleteStateSuccessfully() throws StateException {
		when(stateRepository.findByCountryIdAndNameAndInitials(any(), any(), any()))
				.thenReturn(createState(1L, 1L, "Paraíba", "PB"));
		stateService.deleteState(createStateDto(1L, "Paraíba", "PB"));
	}

	@Test
	public void shouldThrowErrorOfDeleteStateIfCountryIdFieldAreMissing() throws StateException {
		Assert.assertThrows(StateException.class,
				() -> stateService.deleteState(createStateDto(null, "Paraíba", "PB")));
	}

	@Test
	public void shouldThrowErrorOfDeleteStateIfNameFieldAreMissing() throws StateException {
		Assert.assertThrows(StateException.class, () -> stateService.deleteState(createStateDto(1L, null, "PB")));
	}

	@Test
	public void shouldThrowErrorOfDeleteStateIfInitialsFieldAreMissing() throws StateException {
		Assert.assertThrows(StateException.class, () -> stateService.deleteState(createStateDto(1L, "Paraíba", null)));
	}

	@Test
	public void shouldThrowErrorOfDeleteStateIfStateNotFound() throws StateException {
		when(stateRepository.findByCountryIdAndNameAndInitials(any(), any(), any())).thenReturn(null);
		Assert.assertThrows(StateException.class, () -> stateService.deleteState(createStateDto(1L, "Paraíba", "PB")));
	}

	@Test
	public void shouldFoundStateSuccessfully() throws StateException {
		when(stateRepository.findById(any())).thenReturn(Optional.of(createState(1L, 1L, "Paraíba", "PB")));
		Assert.assertNotNull(stateService.searchStateById(1L));
	}

	@Test
	public void shouldThrowErrorOfSearchStateByIdIfIdFieldNotFound() throws StateException {
		when(stateRepository.findById(any())).thenReturn(Optional.empty());
		Assert.assertThrows(StateException.class, () -> stateService.searchStateById(1L));
	}

	private State createState(Long id, Long countryId, String name, String inicials) {
		return State.builder().id(id).countryId(countryId).name(name).initials(inicials).build();
	}

	private StateDTO createStateDto(Long countryId, String name, String inicials) {
		return StateDTO.builder().countryId(countryId).name(name).initials(inicials).build();
	}

}