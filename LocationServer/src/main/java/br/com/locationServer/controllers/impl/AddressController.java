package br.com.locationServer.controllers.impl;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.locationServer.dtos.AddressDTO;
import br.com.locationServer.exception.AddressException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

public interface AddressController {

	@ApiOperation(value = "Registra um endereço")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Retorna dto de duelo com o vencedor"),
	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
	    @ApiResponse(code = 500, message = "Retornara uma mensagem amigável para o usuária"),
	})
	public ResponseEntity<AddressDTO> registerAddress(@RequestBody AddressDTO addressDTO) throws AddressException;

	@ApiOperation(value = "Lista todos os endereços")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Retorna dto de duelo com o vencedor"),
	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
	    @ApiResponse(code = 500, message = "Retornara uma mensagem amigável para o usuária"),
	})
	public ResponseEntity<List<AddressDTO>> searchAllAddress() throws AddressException;

	@ApiOperation(value = "Busca um endereço por id")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Retorna dto de duelo com o vencedor"),
	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
	    @ApiResponse(code = 500, message = "Retornara uma mensagem amigável para o usuária"),
	})
	public ResponseEntity<AddressDTO> searchAddressById(@PathVariable("addressId") Long addressId) throws AddressException;

	@ApiOperation(value = "Atualiza um endereço")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Retorna dto de duelo com o vencedor"),
	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
	    @ApiResponse(code = 500, message = "Retornara uma mensagem amigável para o usuária"),
	})
	public ResponseEntity<AddressDTO> updateAddress(@RequestBody AddressDTO addressDTO) throws AddressException;

	@ApiOperation(value = "Deleta um endereço")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Retorna dto de duelo com o vencedor"),
	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
	    @ApiResponse(code = 500, message = "Retornara uma mensagem amigável para o usuária"),
	})
	public void deleteAddress(@RequestBody AddressDTO addressDTO) throws AddressException;

}