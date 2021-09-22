package br.com.userServer.entitys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import br.com.userServer.enums.GenderEnum;
import br.com.userServer.interfaces.Person;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
@Table(name = "TB_TEACHER")
@GenericGenerator(
        name = "SEQ_TEACHER",
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = {
                @Parameter(name = "sequence_name", value = "SEQ_TEACHER"),
                @Parameter(name = "initial_value", value = "1"),
                @Parameter(name = "increment_size", value = "1")
        }
)
public class Teacher implements Person {

	private static final long serialVersionUID = 3674692518110352647L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TEACHER")
	@Column(name = "ID_TEACHER", nullable = false)
	private Long id;

	@Column(name = "ID_ADDRESS", nullable = false)
	private Long address;

	@Column(name = "ID_CONTACT", nullable = false)
	private Long contact;

	@Column(name = "NR_AGE", nullable = false)
	private Integer age;

	@Column(name = "NM_NAME", nullable = false)
	private String name;

	@Column(name = "NM_COURSE", nullable = false)
	private String course;

	@Column(name = "NM_FORMATION", nullable = false)
	private String formation;

	@Column(name = "DS_GENDER", nullable = false)
	@Enumerated(EnumType.STRING)
	private GenderEnum gender;

}