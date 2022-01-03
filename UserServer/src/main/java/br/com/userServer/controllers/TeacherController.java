package br.com.userServer.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.userServer.dtos.TeacherDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

public interface TeacherController {

	@ApiOperation(value = "Registra um professor")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Retorna um dto de professor"),
	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
	    @ApiResponse(code = 500, message = "Retornara uma mensagem amigável para o usuário"),
	})
	public ResponseEntity<TeacherDTO> registerTeacher(@RequestBody TeacherDTO teacherDto);

	@ApiOperation(value = "Lista todos os professores")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Retorna uma lista de dto de professor"),
	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
	    @ApiResponse(code = 500, message = "Retornara uma mensagem amigável para o usuário"),
	})
	public ResponseEntity<List<TeacherDTO>> searchAllTeachers();

	@ApiOperation(value = "Busca um professor por id")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Retorna um dto de professor"),
	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
	    @ApiResponse(code = 500, message = "Retornara uma mensagem amigável para o usuário"),
	})
	public ResponseEntity<TeacherDTO> searchTeacherById(@PathVariable("teacherId") Long teacherId);

	@ApiOperation(value = "Atualiza um professor")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Retorna um dto de professor"),
	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
	    @ApiResponse(code = 500, message = "Retornara uma mensagem amigável para o usuário"),
	})
	public ResponseEntity<TeacherDTO> updateTeacher(@RequestBody TeacherDTO teacherDto);

	@ApiOperation(value = "Deleta um professor")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Não possui retorno"),
	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
	    @ApiResponse(code = 500, message = "Retornara uma mensagem amigável para o usuário"),
	})
	public ResponseEntity<TeacherDTO> deleteTeacher(@RequestBody TeacherDTO teacherDto);

}