package br.com.userServer.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.userServer.dtos.StudentDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

public interface StudentController {

	@ApiOperation(value = "Registra um estudante")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Retorna um dto de estudante"),
	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
	    @ApiResponse(code = 500, message = "Retornara uma mensagem amigável para o usuário"),
	})
	public ResponseEntity<StudentDTO> registerStudent(@RequestBody StudentDTO studentDto);

	@ApiOperation(value = "Lista todos os estudantes")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Retorna uma lista de dto de estudante"),
	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
	    @ApiResponse(code = 500, message = "Retornara uma mensagem amigável para o usuário"),
	})
	public ResponseEntity<List<StudentDTO>> searchAllStudents();

	@ApiOperation(value = "Busca um estudante por id")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Retorna um dto de estudante"),
	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
	    @ApiResponse(code = 500, message = "Retornara uma mensagem amigável para o usuário"),
	})
	public ResponseEntity<StudentDTO> searchStudentById(@PathVariable("studentId") Long studentId);

	@ApiOperation(value = "Atualiza um estudante")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Retorna um dto de estudante"),
	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
	    @ApiResponse(code = 500, message = "Retornara uma mensagem amigável para o usuário"),
	})
	public ResponseEntity<StudentDTO> updateStudent(@RequestBody StudentDTO studentDto);

	@ApiOperation(value = "Deleta um estudante")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Não possui retorno"),
	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
	    @ApiResponse(code = 500, message = "Retornara uma mensagem amigável para o usuário"),
	})
	public ResponseEntity<StudentDTO> deleteStudent(@RequestBody StudentDTO studentDto);

}