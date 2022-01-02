package br.com.userServer.services;

import java.util.List;

import br.com.userServer.dtos.TeacherDTO;
import br.com.userServer.entitys.Teacher;
import br.com.userServer.exceptions.TeacherException;

public interface ITeacherService {

	public Teacher registerTeacher(TeacherDTO teacherDto) throws TeacherException;

	public List<Teacher> searchAllTeachers() throws TeacherException;

	public Teacher searchTeacherByName(String name) throws TeacherException;

	public Teacher searchTeacherById(Long teacherId) throws TeacherException;

	public Teacher updateTeacher(TeacherDTO teacherDto) throws TeacherException;

	public void deleteTeacher(String name) throws TeacherException;

}