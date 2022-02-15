package br.com.userServer.controllers.impl;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionTimedOutException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Stopwatch;

import br.com.userServer.controllers.UserController;
import br.com.userServer.dtos.UserDTO;
import br.com.userServer.exceptions.UserException;
import br.com.userServer.services.IUserService;
import io.micrometer.core.annotation.Timed;

@RestController
@RequestMapping(path = "/user")
@Transactional
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
	@Timed
	@Transactional(timeout = 30)
	public ResponseEntity<UserDTO> registerUser(UserDTO userDto) {
		try {
			return ResponseEntity.ok(UserDTO.convertUserToDto(userService.registerUser(userDto)));
		} catch (UserException exception) {
			exception.printStackTrace();
			return ResponseEntity.status(406).body(UserDTO.createDtoWithMessageError(exception.getMessage()));
		}
	}

	@GetMapping("/search")
	@ResponseBody
	@Override
	@Timed
	@Transactional(timeout = 1)
	public ResponseEntity<List<UserDTO>> searchAllUsers() {
		try {
			Stopwatch watch = Stopwatch.createStarted();
			while (watch.elapsed(TimeUnit.SECONDS) < 2) {
				int i = Integer.MIN_VALUE;
				while (i < Integer.MAX_VALUE) {
					i++;
				}
			}
			return ResponseEntity.ok(UserDTO.convertListUserToListDto(userService.searchAllUsers()));
		} catch (UserException exception) {
			exception.printStackTrace();
			return ResponseEntity.status(204).body(Arrays.asList(UserDTO.createDtoWithMessageError(exception.getMessage())));
		} catch (TransactionTimedOutException e) {
			e.printStackTrace();
			return ResponseEntity.status(503).body(Arrays.asList(UserDTO.createDtoWithMessageError("No momento este serviço está indisponivel. Tente novamente mais tarde.")));
		}
	}

	@GetMapping("/internal/search-by/{userId}")
	@ResponseBody
	@Override
	@Timed
	@Transactional(timeout = 30)
	public ResponseEntity<UserDTO> searchUserById(Long userId) {
		try {
			return ResponseEntity.ok(UserDTO.convertUserToDto(userService.searchUserById(userId)));
		} catch (UserException exception) {
			exception.printStackTrace();
			return ResponseEntity.status(204).body(UserDTO.createDtoWithMessageError(exception.getMessage()));
		}
	}

	@PutMapping("/update")
	@ResponseBody
	@Override
	@Timed
	@Transactional(timeout = 30)
	public ResponseEntity<UserDTO> updateUser(UserDTO userDto) {
		try {
			return ResponseEntity.ok(UserDTO.convertUserToDto(userService.updateUser(userDto)));
		} catch (UserException exception) {
			exception.printStackTrace();
			return ResponseEntity.status(406).body(UserDTO.createDtoWithMessageError(exception.getMessage()));
		}
	}

	@DeleteMapping("/delete")
	@ResponseBody
	@Override
	@Timed
	@Transactional(timeout = 30)
	public ResponseEntity<UserDTO> deleteUser(UserDTO userDto) {
		try {
			userService.deleteUser(userDto.getEmail());
			return ResponseEntity.status(200).body(UserDTO.createDtoWithMessageError(null));
		} catch (UserException exception) {
			exception.printStackTrace();
			return ResponseEntity.status(406).body(UserDTO.createDtoWithMessageError(exception.getMessage()));
		}
	}

}