package br.com.userServer.services.impl;

import java.util.List;
import java.util.Objects;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import br.com.userServer.dtos.StudentDTO;
import br.com.userServer.entitys.Student;
import br.com.userServer.exceptions.StudentException;
import br.com.userServer.repositorys.IStudentRepository;
import br.com.userServer.services.IStudentService;

@Service
public class StudentServiceImpl implements IStudentService {

	private static final String MSG_ERROR_NENHUM_REGISTRO_ENCONTRADO = "Nenhum registro foi encontrado.";

	private IStudentRepository studentRepository;

	@Autowired
	public StudentServiceImpl(IStudentRepository studentRepository) {
		super();
		this.studentRepository = studentRepository;
	}

	@Override
	public Student registerStudent(StudentDTO studentDto) throws StudentException {
		validateStudent(studentDto);
		return studentRepository.save(StudentDTO.convertDtoToStudent(studentDto));
	}

	@Override
	public List<Student> searchAllStudents() throws StudentException {
		List<Student> studentsFound = studentRepository.findAll();
		if (CollectionUtils.isEmpty(studentsFound)) {
			throw new StudentException(MSG_ERROR_NENHUM_REGISTRO_ENCONTRADO);
		}
		return studentsFound;
	}

	@Override
	public Student searchStudentByRegistration(String registration) throws StudentException {
		if (StringUtils.isBlank(registration)) {
			throw new StudentException("A matricula informada não foi encontrado.");
		}
		Student studentFound = studentRepository.findByRegistration(registration);
		if (Objects.isNull(studentFound)) {
			throw new StudentException(MSG_ERROR_NENHUM_REGISTRO_ENCONTRADO);
		}
		return studentFound;
	}

	@Override
	public Student searchStudentById(Long studentId) throws StudentException {
		Student studentFound = studentRepository.findById(studentId).orElse(null);
		if (Objects.isNull(studentFound)) {
			throw new StudentException(MSG_ERROR_NENHUM_REGISTRO_ENCONTRADO);
		}
		return studentFound;
	}

	@Override
	public Student updateStudent(StudentDTO studentDto) throws StudentException {
		Student studentFound = searchStudentByRegistration(studentDto.getRegistration());
		if (StringUtils.isBlank(studentDto.getNewName())) {
			studentFound.setName(studentDto.getNewName());
		}
		if (StringUtils.isBlank(studentDto.getNewCourse())) {
			studentFound.setCourse(studentDto.getNewCourse());
		}
		if (Objects.isNull(studentDto.getNewAge())) {
			studentFound.setAge(studentDto.getNewAge());
		}
		if (Objects.isNull(studentDto.getNewGender())) {
			studentFound.setGender(studentDto.getNewGender());
		}
		return studentRepository.save(studentFound);
	}

	@Override
	public void deleteStudent(String registration) throws StudentException {
		studentRepository.delete(searchStudentByRegistration(registration));
	}

	protected void validateStudent(StudentDTO studentDto) throws StudentException {
		if (StringUtils.isBlank(studentDto.getName())) {
			throw new StudentException("O nome informado é inválido.");
		}
		if (StringUtils.isBlank(studentDto.getCourse())) {
			throw new StudentException("O curso informado é inválido.");
		}
		if (Objects.isNull(studentDto.getAge())) {
			throw new StudentException("O idade informada é inválida.");
		}
		if (Objects.isNull(studentDto.getGender())) {
			throw new StudentException("O genero informado é inválido.");
		}
	}

}