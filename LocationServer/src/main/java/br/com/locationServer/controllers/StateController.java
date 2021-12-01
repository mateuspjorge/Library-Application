package br.com.locationServer.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.locationServer.dtos.StateDTO;
import br.com.locationServer.exception.StateException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

public interface StateController {

	@ApiOperation(value = "Registra um estado")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Retorna um dto de estado"),
	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
	    @ApiResponse(code = 500, message = "Retornara uma mensagem amigável para o usuário"),
	})
	public ResponseEntity<StateDTO> registerState(@RequestBody StateDTO stateDTO) throws StateException;

	@ApiOperation(value = "Lista todos os estados")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Retorna uma lista de dto de estados"),
	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
	    @ApiResponse(code = 500, message = "Retornara uma mensagem amigável para o usuário"),
	})
	public ResponseEntity<List<StateDTO>> searchAllStates() throws StateException;

	@ApiOperation(value = "Busca um estado por id")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Retorna um dto de estado"),
	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
	    @ApiResponse(code = 500, message = "Retornara uma mensagem amigável para o usuário"),
	})
	public ResponseEntity<StateDTO> searchStateById(@PathVariable("stateId") Long stateId) throws StateException;

	@ApiOperation(value = "Atualiza um estado")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Retorna um dto de estado"),
	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
	    @ApiResponse(code = 500, message = "Retornara uma mensagem amigável para o usuário"),
	})
	public ResponseEntity<StateDTO> updateState(@RequestBody StateDTO stateDTO) throws StateException;

	@ApiOperation(value = "Deleta um estado")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Não possui retorno"),
	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
	    @ApiResponse(code = 500, message = "Retornara uma mensagem amigável para o usuário"),
	})
	public void deleteState(@RequestBody StateDTO stateDTO) throws StateException;

}