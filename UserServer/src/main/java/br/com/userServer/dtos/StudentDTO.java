package br.com.userServer.dtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

import br.com.userServer.entitys.Student;
import br.com.userServer.enums.GenderEnum;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentDTO implements Serializable {

	private static final long serialVersionUID = 1911932152686588860L;

	private Long id;

	private Long address;

	private Long newAddress;

	private Integer age;

	private Integer newAge;

	private String name;

	private String newName;

	private String course;

	private String newCourse;

	private String registration;

	private String messageError;

	private GenderEnum gender;

	private GenderEnum newGender;

	private List<ContactDTO> contacts;

	public static StudentDTO createDtoWithMessageError(String error) {
		return StudentDTO.builder().messageError(error).build();
	}

	public static StudentDTO convertStudentToDto(Student student) {
		return StudentDTO.builder()
					     .id(student.getId())
					     .address(student.getAddress())
					     .age(student.getAge())
					     .name(student.getName())
					     .course(student.getCourse())
					     .registration(student.getRegistration())
					     .gender(student.getGender())
					     .contacts(ContactDTO.convertListContactToListDto(student.getContacts()))
					     .build();
	}

	public static Student convertDtoToStudent(StudentDTO studentDto) {
		return Student.builder()
				      .id(studentDto.getId())
				      .address(studentDto.getAddress())
				      .age(studentDto.getAge())
				      .name(studentDto.getName())
				      .course(studentDto.getCourse())
				      .registration(studentDto.getRegistration())
				      .gender(studentDto.getGender())
				      .contacts(ContactDTO.convertListDtoToListContact(studentDto.getContacts()))
				      .build();
	}
	
	public static List<StudentDTO> convertListStudentToListDto(List<Student> students) {
		List<StudentDTO> studentsDto = new ArrayList<>();
		if (!CollectionUtils.isEmpty(students)) {
			students.forEach(student -> studentsDto.add(convertStudentToDto(student)));
		}
		return studentsDto;
	}

	public static List<Student> convertListDtoToListStudent(List<StudentDTO> studentsDto) {
		List<Student> students = new ArrayList<>();
		if (!CollectionUtils.isEmpty(studentsDto)) {
			studentsDto.forEach(dto -> students.add(convertDtoToStudent(dto)));
		}
		return students;
	}

}