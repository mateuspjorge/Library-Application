package br.com.userServer.services;

import java.util.List;

import br.com.userServer.dtos.ContactDTO;
import br.com.userServer.entitys.Contact;
import br.com.userServer.exceptions.ContactException;

public interface IContactService {

	public Contact registerContact(ContactDTO contactDto) throws ContactException;

	public List<Contact> searchAllContacts() throws ContactException;

	public Contact searchContactById(Long contactId) throws ContactException;

	public Contact updateContact(ContactDTO contactDto) throws ContactException;

	public void deleteContact(String registration) throws ContactException;

}