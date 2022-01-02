package br.com.userServer.dtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

import br.com.userServer.entitys.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO implements Serializable {

	private static final long serialVersionUID = 3768175200111010735L;

	private Long id;

	private String uId;

	private String username;

	private String password;

	private String email;

	private String messageError;

	public static UserDTO createDtoWithMessageError(String error) {
		return UserDTO.builder().messageError(error).build();
	}

	public static UserDTO convertUserToDto(User user) {
		return UserDTO.builder()
					  .id(user.getId())
					  .uId(user.getUId())
					  .username(user.getUsername())
					  .password(user.getPassword())
					  .email(user.getEmail())
					  .build();
	}

	public static User convertDtoToUser(UserDTO userDto) {
		return User.builder()
				   .id(userDto.getId())
				   .uId(userDto.getUId())
				   .username(userDto.getUsername())
				   .password(userDto.getPassword())
				   .email(userDto.getEmail())
				   .build();
	}

	public static List<UserDTO> convertListUserToListDto(List<User> users) {
		List<UserDTO> usersDto = new ArrayList<>();
		if (!CollectionUtils.isEmpty(users)) {
			users.forEach(user -> usersDto.add(convertUserToDto(user)));
		}
		return usersDto;
	}

	public static List<User> convertListDtoToListUser(List<UserDTO> usersDto) {
		List<User> users = new ArrayList<>();
		if (!CollectionUtils.isEmpty(usersDto)) {
			usersDto.forEach(dto -> users.add(convertDtoToUser(dto)));
		}
		return users;
	}

}