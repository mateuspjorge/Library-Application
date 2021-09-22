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

import br.com.userServer.enums.ContactTypeEnum;
import lombok.Builder;
import lombok.Data;

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
public class Contact {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CONTACT")
	@Column(name = "ID_CONTACT", nullable = false)
	private Long id;

	@Column(name = "DS_DESCRIPTION", nullable = false)
	private String description;

	@Column(name = "TP_CONTACT", nullable = false)
	@Enumerated(EnumType.STRING)
	private ContactTypeEnum type;

}