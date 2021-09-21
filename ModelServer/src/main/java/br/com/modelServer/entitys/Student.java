package br.com.modelServer.entitys;

import br.com.modelServer.Person;
import br.com.modelServer.enums.GenderEnum;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Student implements Person {

	private static final long serialVersionUID = 2621764444348217193L;

	private Long id;

	private Long address;

	private Long contact;

	private Long registration;

	private Integer age;

	private String name;

	private String course;

	private GenderEnum gender;

}