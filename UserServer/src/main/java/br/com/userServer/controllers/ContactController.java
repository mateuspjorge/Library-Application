package br.com.userServer.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.userServer.dtos.ContactDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

public interface ContactController {

	@ApiOperation(value = "Registra um contato")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Retorna um dto de contato"),
	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
	    @ApiResponse(code = 500, message = "Retornara uma mensagem amigável para o usuário"),
	})
	public ResponseEntity<ContactDTO> registerContact(@RequestBody ContactDTO contactDto);

	@ApiOperation(value = "Lista todos os contatos")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Retorna uma lista de dto de contato"),
	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
	    @ApiResponse(code = 500, message = "Retornara uma mensagem amigável para o usuário"),
	})
	public ResponseEntity<List<ContactDTO>> searchAllContacts();

	@ApiOperation(value = "Busca um contato por id")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Retorna um dto de contato"),
	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
	    @ApiResponse(code = 500, message = "Retornara uma mensagem amigável para o usuário"),
	})
	public ResponseEntity<ContactDTO> searchContactById(@PathVariable("contactId") Long contactId);

	@ApiOperation(value = "Atualiza um contato")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Retorna um dto de contato"),
	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
	    @ApiResponse(code = 500, message = "Retornara uma mensagem amigável para o usuário"),
	})
	public ResponseEntity<ContactDTO> updateContact(@RequestBody ContactDTO contactDto);

	@ApiOperation(value = "Deleta um contato")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Não possui retorno"),
	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
	    @ApiResponse(code = 500, message = "Retornara uma mensagem amigável para o usuário"),
	})
	public ResponseEntity<ContactDTO> deleteContact(@RequestBody ContactDTO contactDto);

}