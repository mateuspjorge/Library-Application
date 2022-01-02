package br.com.userServer.entitys;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import br.com.userServer.enums.GenderEnum;
import br.com.userServer.interfaces.Person;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@NoArgsConstructor
@AllArgsConstructor
public class Teacher implements Person {

	private static final long serialVersionUID = 3674692518110352647L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TEACHER")
	@Column(name = "ID_TEACHER", nullable = false)
	private Long id;

	@Column(name = "ID_ADDRESS", nullable = false)
	private Long address;

	@Column(name = "NR_AGE", nullable = false)
	private Integer age;

	@Column(name = "DSC_NAME", nullable = false)
	private String name;

	@Column(name = "DSC_COURSE", nullable = false)
	private String course;

	@Column(name = "DSC_FORMATION", nullable = false)
	private String formation;

	@Column(name = "DS_GENDER", nullable = false)
	@Enumerated(EnumType.STRING)
	private GenderEnum gender;

	@OneToOne
	@JoinColumn(name = "ID_USER")
	private User user;

	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH })
	@JoinTable(
			   name = "TB_TEACHER_CONTACT",
			   joinColumns = @JoinColumn(name = "ID_TEACHER",
										 referencedColumnName = "ID_TEACHER"),
			   inverseJoinColumns = @JoinColumn(name = "ID_CONTACT",
												referencedColumnName = "ID_CONTACT"))
	private List<Contact> contacts;

}