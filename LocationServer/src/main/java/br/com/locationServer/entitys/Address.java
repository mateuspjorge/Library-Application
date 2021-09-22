package br.com.locationServer.entitys;

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
@Table(name = "TB_ADDRESS")
@GenericGenerator(
        name = "SEQ_ADDRESS",
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = {
                @Parameter(name = "sequence_name", value = "SEQ_ADDRESS"),
                @Parameter(name = "initial_value", value = "1"),
                @Parameter(name = "increment_size", value = "1")
        }
)
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ADDRESS")
	@Column(name = "ID_ADDRESS", nullable = false)
	private Long id;

	@Column(name = "ID_CITY", nullable = false)
	private Long cityId;

	@Column(name = "NR_NUMBER", nullable = false)
	private Integer number;

	@Column(name = "NR_CEP", nullable = false)
	private String cep;

	@Column(name = "NM_STREET", nullable = false)
	private String street;

	@Column(name = "NM_DISTRICT", nullable = false)
	private String district;

	@Column(name = "NM_COMPLEMENT", nullable = true)
	private String complement;

}