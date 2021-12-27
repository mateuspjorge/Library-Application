package br.com.locationServer.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.locationServer.entitys.Country;

@Repository
public interface ICountryRepository extends JpaRepository<Country, Long> {

	@Query("SELECT country FROM Country country WHERE UPPER(country.name) LIKE %:name%")
	Country findByName(@Param("name") String name);

}