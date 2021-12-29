package br.com.userServer.dtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

import br.com.userServer.entitys.Teacher;
import br.com.userServer.enums.GenderEnum;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TeacherDTO implements Serializable {

	private static final long serialVersionUID = 323629020598402859L;

	private Long id;

	private Long address;

	private Long newAddress;

	private Integer age;

	private Integer newAge;

	private String name;

	private String newName;

	private String course;

	private String newCourse;

	private String formation;

	private String newFormation;

	private String messageError;

	private GenderEnum gender;

	private GenderEnum newGender;

	private List<ContactDTO> contacts;

	public static TeacherDTO createDtoWithMessageError(String error) {
		return TeacherDTO.builder().messageError(error).build();
	}

	public static TeacherDTO convertTeacherToDto(Teacher teacher) {
		return TeacherDTO.builder()
						 .id(teacher.getId())
						 .address(teacher.getAddress())
						 .age(teacher.getAge())
						 .name(teacher.getName())
						 .course(teacher.getCourse())
						 .formation(teacher.getFormation())
						 .gender(teacher.getGender())
						 .contacts(ContactDTO.convertListContactToListDto(teacher.getContacts()))
						 .build();
	}

	public static Teacher convertDtoToTeacher(TeacherDTO teacherDto) {
		return Teacher.builder()
					  .id(teacherDto.getId())
					  .address(teacherDto.getAddress())
					  .age(teacherDto.getAge())
					  .name(teacherDto.getName())
					  .course(teacherDto.getCourse())
					  .formation(teacherDto.getFormation())
					  .gender(teacherDto.getGender())
					  .contacts(ContactDTO.convertListDtoToListContact(teacherDto.getContacts()))
					  .build();
	}

	public static List<TeacherDTO> convertListTeacherToListDto(List<Teacher> teachers) {
		List<TeacherDTO> teachersDto = new ArrayList<>();
		if (!CollectionUtils.isEmpty(teachers)) {
			teachers.forEach(teacher -> teachersDto.add(convertTeacherToDto(teacher)));
		}
		return teachersDto;
	}

	public static List<Teacher> convertListDtoToListContact(List<TeacherDTO> teachersDto) {
		List<Teacher> teachers = new ArrayList<>();
		if (!CollectionUtils.isEmpty(teachersDto)) {
			teachersDto.forEach(dto -> teachers.add(convertDtoToTeacher(dto)));
		}
		return teachers;
	}

}