package br.com.userServer.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.userServer.entitys.Contact;

@Repository
public interface IContactRepository extends JpaRepository<Contact, Long> {

}