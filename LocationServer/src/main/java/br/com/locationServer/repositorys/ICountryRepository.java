package br.com.locationServer.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.locationServer.entitys.Country;

@Repository
public interface ICountryRepository extends JpaRepository<Country, Long> {

}