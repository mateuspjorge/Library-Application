package br.com.locationServer.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.locationServer.dtos.CityDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

public interface CityController {

	@ApiOperation(value = "Registra uma cidade")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Retorna um dto de cidade"),
	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
	    @ApiResponse(code = 500, message = "Retornara uma mensagem amigável para o usuário"),
	})
	public ResponseEntity<CityDTO> registerCity(@RequestBody CityDTO cityDTO);

	@ApiOperation(value = "Lista todos as cidades")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Retorna uma lista de dto de cidades"),
	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
	    @ApiResponse(code = 500, message = "Retornara uma mensagem amigável para o usuário"),
	})
	public ResponseEntity<List<CityDTO>> searchAllCities();

	@ApiOperation(value = "Busca uma cidade por id")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Retorna um dto de cidade"),
	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
	    @ApiResponse(code = 500, message = "Retornara uma mensagem amigável para o usuário"),
	})
	public ResponseEntity<CityDTO> searchCityById(@PathVariable("cityId") Long cityId);

	@ApiOperation(value = "Atualiza uma cidade")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Retorna um dto de cidade"),
	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
	    @ApiResponse(code = 500, message = "Retornara uma mensagem amigável para o usuário"),
	})
	public ResponseEntity<CityDTO> updateCity(@RequestBody CityDTO cityDTO);

	@ApiOperation(value = "Deleta uma cidade")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Não possui retorno"),
	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
	    @ApiResponse(code = 500, message = "Retornara uma mensagem amigável para o usuário"),
	})
	public ResponseEntity<CityDTO> deleteCity(@RequestBody CityDTO cityDTO);

}