package br.com.locationServer.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.locationServer.dtos.AddressDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

public interface AddressController {

	@ApiOperation(value = "Registra um endereço")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Retorna um dto de endereço"),
	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
	    @ApiResponse(code = 500, message = "Retornara uma mensagem amigável para o usuário"),
	})
	public ResponseEntity<AddressDTO> registerAddress(@RequestBody AddressDTO addressDTO);

	@ApiOperation(value = "Lista todos os endereços")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Retorna uma lista de dto de endereços"),
	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
	    @ApiResponse(code = 500, message = "Retornara uma mensagem amigável para o usuário"),
	})
	public ResponseEntity<List<AddressDTO>> searchAllAddress();

	@ApiOperation(value = "Busca um endereço por id")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Retorna um dto de endereço"),
	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
	    @ApiResponse(code = 500, message = "Retornara uma mensagem amigável para o usuário"),
	})
	public ResponseEntity<AddressDTO> searchAddressById(@PathVariable("addressId") Long addressId);

	@ApiOperation(value = "Atualiza um endereço")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Retorna um dto de endereço"),
	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
	    @ApiResponse(code = 500, message = "Retornara uma mensagem amigável para o usuário"),
	})
	public ResponseEntity<AddressDTO> updateAddress(@RequestBody AddressDTO addressDTO);

	@ApiOperation(value = "Deleta um endereço")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Não possui retorno"),
	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
	    @ApiResponse(code = 500, message = "Retornara uma mensagem amigável para o usuário"),
	})
	public ResponseEntity<AddressDTO> deleteAddress(@RequestBody AddressDTO addressDTO);

}