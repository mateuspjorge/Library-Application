package br.com.userServer.dtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

import br.com.userServer.entitys.Contact;
import br.com.userServer.enums.ContactTypeEnum;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContactDTO implements Serializable {

	private static final long serialVersionUID = -1590323457633017020L;

	private Long id;

	private String description;

	private String newDescription;

	private String messageError;

	private StudentDTO student;

	private StudentDTO newStudent;

	private TeacherDTO teacher;

	private TeacherDTO newTeacher;

	private ContactTypeEnum type;

	private ContactTypeEnum newType;

	public static ContactDTO createDtoWithMessageError(String error) {
		return ContactDTO.builder().messageError(error).build();
	}

	public static ContactDTO convertContactToDto(Contact contact) {
		return ContactDTO.builder()
						 .id(contact.getId())
						 .description(contact.getDescription())
						 .student(StudentDTO.convertStudentToDto(contact.getStudent()))
						 .teacher(TeacherDTO.convertTeacherToDto(contact.getTeacher()))
						 .type(contact.getType())
						 .build();
	}

	public static Contact convertDtoToContact(ContactDTO contactDto) {
		return Contact.builder()
					  .id(contactDto.getId())
					  .description(contactDto.getDescription())
					  .student(StudentDTO.convertDtoToStudent(contactDto.getStudent()))
					  .teacher(TeacherDTO.convertDtoToTeacher(contactDto.getTeacher()))
					  .type(contactDto.getType())
					  .build();
	}

	public static List<ContactDTO> convertListContactToListDto(List<Contact> contacts) {
		List<ContactDTO> contactsDto = new ArrayList<>();
		if (!CollectionUtils.isEmpty(contacts)) {
			contacts.forEach(contact -> contactsDto.add(convertContactToDto(contact)));
		}
		return contactsDto;
	}

	public static List<Contact> convertListDtoToListContact(List<ContactDTO> contactsDto) {
		List<Contact> contacts = new ArrayList<>();
		if (!CollectionUtils.isEmpty(contactsDto)) {
			contactsDto.forEach(dto -> contacts.add(convertDtoToContact(dto)));
		}
		return contacts;
	}

}