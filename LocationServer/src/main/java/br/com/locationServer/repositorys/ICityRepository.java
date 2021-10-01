package br.com.locationServer.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.locationServer.entitys.City;

@Repository
public interface ICityRepository extends JpaRepository<City, Long> {

	@Query("SELECT * "
		 + "FROM TB_CITY city "
		 + "WHERE city.ID_STATE = :stateId "
		 + "AND UPPER(city.DSC_NAME) LIKE UPPER(%:name%)")
	City findByStateIdAndName(@Param("stateId") Long stateId, @Param("name") String name);

}