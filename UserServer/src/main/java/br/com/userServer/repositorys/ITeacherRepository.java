package br.com.userServer.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.userServer.entitys.Teacher;

@Repository
public interface ITeacherRepository extends JpaRepository<Teacher, Long> {

}