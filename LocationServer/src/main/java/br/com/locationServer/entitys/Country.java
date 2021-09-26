package br.com.locationServer.entitys;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
@Table(name = "TB_COUNTRY")
@GenericGenerator(
        name = "SEQ_COUNTRY",
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = {
                @Parameter(name = "sequence_name", value = "SEQ_COUNTRY"),
                @Parameter(name = "initial_value", value = "1"),
                @Parameter(name = "increment_size", value = "1")
        }
)
public class Country implements Serializable {

	private static final long serialVersionUID = -5049507472994103076L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_COUNTRY")
	@Column(name = "ID_COUNTRY", nullable = false)
	private Long id;

	@Column(name = "NM_NAME", nullable = false, length = 30)
	private String name;

}