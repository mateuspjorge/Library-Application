package br.com.locationServer.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.locationServer.entitys.City;

@Repository
public interface ICityRepository extends JpaRepository<City, Long> {

	@Query(value = "SELECT * "
				 + "FROM TB_CITY city "
				 + "WHERE UPPER(city.DSC_NAME) LIKE UPPER(%:name%) ", nativeQuery = true)
	City findByName(@Param("name") String name);

}