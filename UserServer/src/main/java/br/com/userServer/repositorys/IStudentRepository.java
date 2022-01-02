package br.com.userServer.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.userServer.entitys.Student;

@Repository
public interface IStudentRepository extends JpaRepository<Student, Long> {

}