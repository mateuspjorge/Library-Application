package br.com.locationServer.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.locationServer.entitys.City;

@Repository
public interface ICityRepository extends JpaRepository<City, Long> {

}