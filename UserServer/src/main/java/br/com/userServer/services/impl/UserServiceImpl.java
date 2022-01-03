package br.com.userServer.services.impl;

import java.util.List;
import java.util.Objects;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import br.com.userServer.dtos.UserDTO;
import br.com.userServer.entitys.User;
import br.com.userServer.exceptions.UserException;
import br.com.userServer.repositorys.IUserRepository;
import br.com.userServer.services.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	private static final String MSG_ERROR_NENHUM_REGISTRO_ENCONTRADO = "Nenhum registro foi encontrado.";

	private IUserRepository userRepository;

	@Autowired
	public UserServiceImpl(IUserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public User registerUser(UserDTO userDto) throws UserException {
		validateUser(userDto);
		return userRepository.save(UserDTO.convertDtoToUser(userDto));
	}

	@Override
	public List<User> searchAllUsers() throws UserException {
		List<User> usersFound = userRepository.findAll();
		if (CollectionUtils.isEmpty(usersFound)) {
			throw new UserException(MSG_ERROR_NENHUM_REGISTRO_ENCONTRADO);
		}
		return usersFound;
	}

	@Override
	public User searchUserByEmail(String email) throws UserException {
		if (StringUtils.isBlank(email)) {
			throw new UserException("O email informado não foi encontrado.");
		}
		User userFound = userRepository.findByEmail(email.toUpperCase());
		if (Objects.isNull(userFound)) {
			throw new UserException(MSG_ERROR_NENHUM_REGISTRO_ENCONTRADO);
		}
		return userFound;
	}

	@Override
	public User searchUserById(Long userId) throws UserException {
		User userFound = userRepository.findById(userId).orElse(null);
		if (Objects.isNull(userFound)) {
			throw new UserException(MSG_ERROR_NENHUM_REGISTRO_ENCONTRADO);
		}
		return userFound;
	}

	@Override
	public User updateUser(UserDTO userDto) throws UserException {
		User userFound = searchUserByEmail(userDto.getEmail());
		if (StringUtils.isBlank(userDto.getNewUsername())) {
			userFound.setUsername(userDto.getNewUsername());
		}
		if (StringUtils.isBlank(userDto.getNewPassword())) {
			userFound.setPassword(userDto.getNewPassword());
		}
		if (StringUtils.isBlank(userDto.getNewEmail())) {
			userFound.setEmail(userDto.getNewEmail());
		}
		return userRepository.save(userFound);
	}

	@Override
	public void deleteUser(String email) throws UserException {
		userRepository.delete(searchUserByEmail(email));
	}

	protected void validateUser(UserDTO userDto) throws UserException {
		if (StringUtils.isBlank(userDto.getUsername())) {
			throw new UserException("O nome de usuário informado é inválido.");
		}
		if (StringUtils.isBlank(userDto.getPassword())) {
			throw new UserException("A senha informada é inválida.");
		}
		if (StringUtils.isBlank(userDto.getEmail())) {
			throw new UserException("O email informado é inválido.");
		}
	}

}