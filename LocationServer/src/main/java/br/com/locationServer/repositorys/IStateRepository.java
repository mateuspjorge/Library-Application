package br.com.locationServer.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.locationServer.entitys.State;

@Repository
public interface IStateRepository extends JpaRepository<State, Long> {

	@Query("SELECT state "
		 + "FROM State state "
		 + "WHERE UPPER(state.name) LIKE %:name% "
		 + "AND UPPER(state.initials) LIKE %:initials%")
	State findByNameAndInitials(@Param("name") String name,
								@Param("initials") String initials);

}