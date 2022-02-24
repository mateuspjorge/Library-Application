package br.com.userServer.controllers.impl;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.userServer.controllers.UserController;
import br.com.userServer.dtos.UserDTO;
import br.com.userServer.exceptions.UserException;
import br.com.userServer.services.IUserService;

@RestController
@RequestMapping(path = "/user")
public class UserControllerImpl implements UserController {

	private IUserService userService;

	@Autowired
	public UserControllerImpl(IUserService userService) {
		super();
		this.userService = userService;
	}

	@PostMapping("/register")
	@ResponseBody
	@Override
	public ResponseEntity<UserDTO> registerUser(UserDTO userDto) {
		try {
			return ResponseEntity.ok(UserDTO.convertUserToDto(userService.registerUser(userDto)));
		} catch (UserException exception) {
			exception.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
								 .body(UserDTO.createDtoWithMessageError(exception.getMessage()));
		}
	}

	@GetMapping("/search")
	@ResponseBody
	@Override
	public Callable<ResponseEntity<List<UserDTO>>> searchAllUsers() {
		return () -> {
			try {
				return ResponseEntity.ok(UserDTO.convertListUserToListDto(userService.searchAllUsers()));
			} catch (UserException exception) {
				exception.printStackTrace();
				return ResponseEntity.status(HttpStatus.NO_CONTENT)
									 .body(Arrays.asList(UserDTO.createDtoWithMessageError(exception.getMessage())));
			}
		};
	}

	@GetMapping("/internal/search-by/{userId}")
	@ResponseBody
	@Override
	public ResponseEntity<UserDTO> searchUserById(Long userId) {
		try {
			return ResponseEntity.ok(UserDTO.convertUserToDto(userService.searchUserById(userId)));
		} catch (UserException exception) {
			exception.printStackTrace();
			return ResponseEntity.status(HttpStatus.NO_CONTENT)
						 		 .body(UserDTO.createDtoWithMessageError(exception.getMessage()));
		}
	}

	@PutMapping("/update")
	@ResponseBody
	@Override
	public ResponseEntity<UserDTO> updateUser(UserDTO userDto) {
		try {
			return ResponseEntity.ok(UserDTO.convertUserToDto(userService.updateUser(userDto)));
		} catch (UserException exception) {
			exception.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
								 .body(UserDTO.createDtoWithMessageError(exception.getMessage()));
		}
	}

	@DeleteMapping("/delete")
	@ResponseBody
	@Override
	public ResponseEntity<UserDTO> deleteUser(UserDTO userDto) {
		try {
			userService.deleteUser(userDto.getEmail());
			return ResponseEntity.status(HttpStatus.OK).body(UserDTO.createDtoWithMessageError(null));
		} catch (UserException exception) {
			exception.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
								 .body(UserDTO.createDtoWithMessageError(exception.getMessage()));
		}
	}

}