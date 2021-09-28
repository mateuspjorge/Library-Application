package br.com.locationServer.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.locationServer.entitys.State;

@Repository
public interface IStateRepository extends JpaRepository<State, Long> {

	@Query("SELECT * "
		 + "FROM TB_STATE state "
		 + "WHERE UPPER(state.NM_NAME) LIKE UPPER(%:name%) "
		 + "AND UPPER(state.DS_INITIALS) LIKE UPPER(%:initials%)")
	State findByNameAndInitials(@Param("name") String name, @Param("initials") String initials);

}