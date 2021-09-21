package br.com.modelServer.entitys;

import br.com.modelServer.Person;
import br.com.modelServer.enums.GenderEnum;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Teacher implements Person {

	private static final long serialVersionUID = 3674692518110352647L;

	private Long id;

	private Long address;

	private Long contact;

	private Integer age;

	private String name;

	private String course;

	private String formation;

	private GenderEnum gender;

}