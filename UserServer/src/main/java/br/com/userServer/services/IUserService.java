package br.com.userServer.services;

import java.util.List;

import br.com.userServer.dtos.UserDTO;
import br.com.userServer.entitys.User;
import br.com.userServer.exceptions.UserException;

public interface IUserService {

	public User registerUser(UserDTO userDto) throws UserException;

	public List<User> searchAllUsers() throws UserException;

	public User searchUserByEmail(String email) throws UserException;

	public User searchUserById(Long userId) throws UserException;

	public User updateUser(UserDTO userDto) throws UserException;

	public void deleteUser(String email) throws UserException;

}