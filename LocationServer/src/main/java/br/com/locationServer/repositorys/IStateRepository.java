package br.com.locationServer.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.locationServer.entitys.State;

@Repository
public interface IStateRepository extends JpaRepository<State, Long> {

	@Query(value = "SELECT * "
				 + "FROM TB_STATE state "
				 + "WHERE state.ID_COUNTRY = :countryId"
				 + "AND UPPER(state.DSC_NAME) LIKE UPPER(%:name%) "
				 + "AND UPPER(state.DSC_INITIALS) LIKE UPPER(%:initials%)", nativeQuery = true)
	State findByCountryIdAndNameAndInitials(@Param("countryId") Long countryId,
											@Param("name") String name,
											@Param("initials") String initials);

}