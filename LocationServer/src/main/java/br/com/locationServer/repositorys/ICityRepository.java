package br.com.locationServer.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.locationServer.entitys.City;

@Repository
public interface ICityRepository extends JpaRepository<City, Long> {

	@Query("SELECT city FROM City city WHERE UPPER(city.name) LIKE %:name% ")
	City findByName(@Param("name") String name);

}