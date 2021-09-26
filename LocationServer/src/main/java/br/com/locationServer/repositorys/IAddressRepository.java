package br.com.locationServer.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.locationServer.entitys.Address;

@Repository
public interface IAddressRepository extends JpaRepository<Address, Long> {

}