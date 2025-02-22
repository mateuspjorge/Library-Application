package br.com.userServer.controllers;

import java.util.List;
import java.util.concurrent.Callable;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.userServer.dtos.UserDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

public interface UserController {

	@ApiOperation(value = "Registra um usuário")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Retorna um dto de usuário"),
	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
	    @ApiResponse(code = 500, message = "Retornara uma mensagem amigável para o usuário"),
	    @ApiResponse(code = 503, message = "Retorna uma mensagem informando que o serviço não está disponivel"),
	})
	public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO userDto);

	@ApiOperation(value = "Lista todos os usuários")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Retorna uma lista de dto de usuário"),
	    @ApiResponse(code = 204, message = "Nenhum registro encontrado"),
	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
	    @ApiResponse(code = 500, message = "Retornara uma mensagem amigável para o usuário"),
	    @ApiResponse(code = 503, message = "Retorna uma mensagem informando que o serviço não está disponivel"),
	})
	public Callable<ResponseEntity<List<UserDTO>>> searchAllUsers();

	@ApiOperation(value = "Busca um usuário por id")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Retorna um dto de usuário"),
	    @ApiResponse(code = 204, message = "Nenhum registro encontrado"),
	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
	    @ApiResponse(code = 500, message = "Retornara uma mensagem amigável para o usuário"),
	    @ApiResponse(code = 503, message = "Retorna uma mensagem informando que o serviço não está disponivel"),
	})
	public ResponseEntity<UserDTO> searchUserById(@PathVariable("userId") Long userId);

	@ApiOperation(value = "Atualiza um usuário")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Retorna um dto de usuário"),
	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
	    @ApiResponse(code = 500, message = "Retornara uma mensagem amigável para o usuário"),
	    @ApiResponse(code = 503, message = "Retorna uma mensagem informando que o serviço não está disponivel"),
	})
	public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDto);

	@ApiOperation(value = "Deleta um usuário")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Não possui retorno"),
	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
	    @ApiResponse(code = 500, message = "Retornara uma mensagem amigável para o usuário"),
	    @ApiResponse(code = 503, message = "Retorna uma mensagem informando que o serviço não está disponivel"),
	})
	public ResponseEntity<UserDTO> deleteUser(@RequestBody UserDTO userDto);

}