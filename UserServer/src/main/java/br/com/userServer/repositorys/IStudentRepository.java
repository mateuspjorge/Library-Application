package br.com.userServer.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.userServer.entitys.Student;

@Repository
public interface IStudentRepository extends JpaRepository<Student, Long> {

	@Query("SELECT student FROM Student student WHERE student.registration = :registration ")
	Student findByRegistration(@Param("registration") String registration);

}