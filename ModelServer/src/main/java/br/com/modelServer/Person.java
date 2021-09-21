package br.com.modelServer;

import java.io.Serializable;

import br.com.modelServer.enums.GenderEnum;

public interface Person extends Serializable {

	Long getId();

	void setId(Long id);

	Long getAddress();

	void setAddress(Long address);

	Long getContact();

	void setContact(Long contact);

	Integer getAge();

	void setAge(Integer age);

	String getName();

	void setName(String name);

	GenderEnum getGender();

	void setGender(GenderEnum gender);

}