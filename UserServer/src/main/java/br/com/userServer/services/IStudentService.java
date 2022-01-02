package br.com.userServer.services;

import java.util.List;

import br.com.userServer.dtos.StudentDTO;
import br.com.userServer.entitys.Student;
import br.com.userServer.exceptions.StudentException;

public interface IStudentService {

	public Student registerStudent(StudentDTO StudentDto) throws StudentException;

	public List<Student> searchAllStudents() throws StudentException;

	public Student searchStudentByRegistration(String registration) throws StudentException;

	public Student searchStudentById(Long studentId) throws StudentException;

	public Student updateStudent(StudentDTO studentDto) throws StudentException;

	public void deleteStudent(String registration) throws StudentException;

}