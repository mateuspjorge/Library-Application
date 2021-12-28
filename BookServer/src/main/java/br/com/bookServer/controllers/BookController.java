package br.com.bookServer.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.bookServer.dtos.BookDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

public interface BookController {

	@ApiOperation(value = "Registra um livro")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Retorna um dto de livro"),
	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
	    @ApiResponse(code = 500, message = "Retornara uma mensagem amigável para o usuário"),
	})
	public ResponseEntity<BookDTO> registerBook(@RequestBody BookDTO bookDto);

	@ApiOperation(value = "Lista todos os livros")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Retorna uma lista de dto de livro"),
	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
	    @ApiResponse(code = 500, message = "Retornara uma mensagem amigável para o usuário"),
	})
	public ResponseEntity<List<BookDTO>> searchAllBooks();

	@ApiOperation(value = "Busca um livro por id")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Retorna um dto de livro"),
	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
	    @ApiResponse(code = 500, message = "Retornara uma mensagem amigável para o usuário"),
	})
	public ResponseEntity<BookDTO> searchBookById(@PathVariable("bookId") Long bookId);

	@ApiOperation(value = "Atualiza um livro")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Retorna um dto de livro"),
	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
	    @ApiResponse(code = 500, message = "Retornara uma mensagem amigável para o usuário"),
	})
	public ResponseEntity<BookDTO> updateBook(@RequestBody BookDTO bookDto);

	@ApiOperation(value = "Deleta um livro")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Não possui retorno"),
	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
	    @ApiResponse(code = 500, message = "Retornara uma mensagem amigável para o usuário"),
	})
	public ResponseEntity<BookDTO> deleteBook(@RequestBody BookDTO bookDto);
	
}