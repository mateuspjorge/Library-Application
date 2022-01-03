package br.com.userServer.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.userServer.entitys.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

	@Query("SELECT user FROM User user WHERE UPPER(user.email) LIKE %:email% ")
	User findByEmail(@Param("email") String email);

}