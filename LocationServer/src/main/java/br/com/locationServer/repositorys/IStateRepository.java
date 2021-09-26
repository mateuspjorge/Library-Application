package br.com.locationServer.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.locationServer.entitys.State;

@Repository
public interface IStateRepository extends JpaRepository<State, Long> {

}