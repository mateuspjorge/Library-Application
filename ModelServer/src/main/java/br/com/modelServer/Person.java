package br.com.modelServer;

import java.io.Serializable;

import br.com.modelServer.enums.GenderEnum;

public abstract class Person<ID extends Serializable> implements Serializable {

	private static final long serialVersionUID = -2652636666826647702L;

	private Long id;

	private Long address;

	private Long contact;

	private Integer age;

	private String name;

	private GenderEnum gender;

}