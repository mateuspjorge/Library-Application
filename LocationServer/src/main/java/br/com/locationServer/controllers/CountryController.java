package br.com.locationServer.controllers;

import java.util.List;
import java.util.concurrent.Callable;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.locationServer.dtos.CountryDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

public interface CountryController {

	@ApiOperation(value = "Registra um país")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Retorna um dto de país"),
	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
	    @ApiResponse(code = 500, message = "Retornara uma mensagem amigável para o usuário"),
	})
	public ResponseEntity<CountryDTO> registerCountry(@RequestBody CountryDTO countryDTO);

	@ApiOperation(value = "Lista todos os países")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Retorna uma lista de dto de países"),
	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
	    @ApiResponse(code = 500, message = "Retornara uma mensagem amigável para o usuário"),
	})
	public Callable<ResponseEntity<List<CountryDTO>>> searchAllCountries();

	@ApiOperation(value = "Busca um país por id")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Retorna um dto de país"),
	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
	    @ApiResponse(code = 500, message = "Retornara uma mensagem amigável para o usuário"),
	})
	public ResponseEntity<CountryDTO> searchCountryById(@PathVariable("countryId") Long countryId);

	@ApiOperation(value = "Atualiza um país")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Retorna um dto de país"),
	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
	    @ApiResponse(code = 500, message = "Retornara uma mensagem amigável para o usuário"),
	})
	public ResponseEntity<CountryDTO> updateCountry(@RequestBody CountryDTO countryDTO);

	@ApiOperation(value = "Deleta um país")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Não possui retorno"),
	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
	    @ApiResponse(code = 500, message = "Retornara uma mensagem amigável para o usuário"),
	})
	public ResponseEntity<CountryDTO> deleteCountry(@RequestBody CountryDTO countryDTO);

}