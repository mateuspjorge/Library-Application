package br.com.userServer.interfaces;

import java.io.Serializable;
import java.util.List;

import br.com.userServer.entitys.Contact;
import br.com.userServer.enums.GenderEnum;

public interface Person extends Serializable {

	Long getId();

	void setId(Long id);

	Long getAddress();

	void setAddress(Long address);

	Integer getAge();

	void setAge(Integer age);

	String getName();

	void setName(String name);

	GenderEnum getGender();

	void setGender(GenderEnum gender);

	List<Contact> getContacts();

	void setContacts(List<Contact> contacts);

}