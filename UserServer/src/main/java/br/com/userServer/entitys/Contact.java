package br.com.userServer.entitys;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import br.com.userServer.enums.ContactTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@Table(name = "TB_CONTACT")
@GenericGenerator(
        name = "SEQ_CONTACT",
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = {
                @Parameter(name = "sequence_name", value = "SEQ_CONTACT"),
                @Parameter(name = "initial_value", value = "1"),
                @Parameter(name = "increment_size", value = "1")
        }
)
@NoArgsConstructor
@AllArgsConstructor
public class Contact implements Serializable {

	private static final long serialVersionUID = -8103277099053489010L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CONTACT")
	@Column(name = "ID_CONTACT", nullable = false)
	private Long id;

	@Column(name = "DSC_CONTACT", nullable = false)
	private String description;

	@ManyToOne
	@JoinColumn(name = "ID_STUDENT")
	private Student student;

	@ManyToOne
	@JoinColumn(name = "ID_TEACHER")
	private Teacher teacher;

	@Column(name = "TP_CONTACT", nullable = false)
	@Enumerated(EnumType.STRING)
	private ContactTypeEnum type;

}