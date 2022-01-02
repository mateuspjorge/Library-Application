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

import br.com.locationServer.dtos.AddressDTO;
import br.com.locationServer.entitys.Address;
import br.com.locationServer.exceptions.AddressException;
import br.com.locationServer.repositorys.IAddressRepository;
import br.com.locationServer.services.impl.AddressServiceImpl;

@SpringBootTest
public class AddressServiceTest {

	@Mock
	private IAddressRepository addressRepository;

	@InjectMocks
	private AddressServiceImpl addressService;

	@SuppressWarnings("deprecation")
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldRegisterAddressSuccessfully() throws AddressException {
		AddressDTO dto = createAddressDto(1L, 1, "11222333", "R. Infante Dom Henrique", "Tambaú");
		when(addressRepository.save(any())).thenReturn(createAddress());
		Assert.assertNotNull(addressService.registerAddress(dto));
	}

	@Test
	public void shouldThrowErrorOfRegisterAddressIfCityIdFieldAreMissing() throws AddressException {
		AddressDTO dto = createAddressDto(null, 1, "11222333", "R. Infante Dom Henrique", "Tambaú");
		when(addressRepository.save(any())).thenReturn(createAddress());
		Assert.assertThrows(AddressException.class, () -> addressService.registerAddress(dto));
	}

	@Test
	public void shouldThrowErrorOfRegisterAddressIfNumberFieldAreMissing() throws AddressException {
		AddressDTO dto = createAddressDto(1L, null, "11222333", "R. Infante Dom Henrique", "Tambaú");
		when(addressRepository.save(any())).thenReturn(createAddress());
		Assert.assertThrows(AddressException.class, () -> addressService.registerAddress(dto));
	}

	@Test
	public void shouldThrowErrorOfRegisterAddressIfCepFieldAreMissing() throws AddressException {
		AddressDTO dto = createAddressDto(1L, 1, null, "R. Infante Dom Henrique", "Tambaú");
		when(addressRepository.save(any())).thenReturn(createAddress());
		Assert.assertThrows(AddressException.class, () -> addressService.registerAddress(dto));
	}

	@Test
	public void shouldThrowErrorOfRegisterAddressIfStreetFieldAreMissing() throws AddressException {
		AddressDTO dto = createAddressDto(1L, 1, "11222333", null, "Tambaú");
		when(addressRepository.save(any())).thenReturn(createAddress());
		Assert.assertThrows(AddressException.class, () -> addressService.registerAddress(dto));
	}

	@Test
	public void shouldThrowErrorOfRegisterAddressIfDistrictFieldAreMissing() throws AddressException {
		AddressDTO dto = createAddressDto(1L, 1, "11222333", "R. Infante Dom Henrique", null);
		when(addressRepository.save(any())).thenReturn(createAddress());
		Assert.assertThrows(AddressException.class, () -> addressService.registerAddress(dto));
	}

	@Test
	public void shouldListAllAddressSuccessfully() throws AddressException {
		when(addressRepository.findAll()).thenReturn(Arrays.asList(createAddress()));
		Assert.assertEquals(1, addressService.searchAllAddress().size());
	}

	@Test
	public void shouldThrowErrorOfAddressListEmpty() throws AddressException {
		when(addressRepository.findAll()).thenReturn(Arrays.asList());
		Assert.assertThrows(AddressException.class, () -> addressService.searchAllAddress());
	}

	@Test
	public void shouldDeleteAddressSuccessfully() throws AddressException {
		AddressDTO dto = createAddressDto(1L, 1, "11222333", "R. Infante Dom Henrique", "Tambaú");
		when(addressRepository.findById(any())).thenReturn(Optional.of(createAddress()));
		addressService.deleteAddress(dto);
	}

	private Address createAddress() {
		return Address.builder()
					  .id(1L)
					  .cityId(1L)
					  .number(1)
					  .cep("11222333")
					  .street("R. Infante Dom Henrique")
					  .district("Tambaú")
					  .complement(null)
					  .build();
	}

	private AddressDTO createAddressDto(Long cityId, Integer number, String cep, String street, String district) {
		return AddressDTO.builder()
					  	 .cityId(cityId)
					  	 .number(number)
					  	 .cep(cep)
					  	 .street(street)
					  	 .district(district)
					  	 .complement(null)
					  	 .build();
	}

}