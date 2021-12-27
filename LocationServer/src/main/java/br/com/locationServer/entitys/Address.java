package br.com.locationServer.entitys;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@NoArgsConstructor
@AllArgsConstructor
public class Address implements Serializable {

	private static final long serialVersionUID = 7158247512827384338L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ADDRESS")
	@Column(name = "ID_ADDRESS", nullable = false)
	private Long id;

	@Column(name = "NR_NUMBER", nullable = false)
	private Integer number;

	@Column(name = "NR_CEP", nullable = false)
	private String cep;

	@Column(name = "DSC_STREET", nullable = false)
	private String street;

	@Column(name = "DSC_DISTRICT", nullable = false)
	private String district;

	@Column(name = "DSC_COMPLEMENT", nullable = true)
	private String complement;

	@ManyToOne
	@JoinColumn(name = "ID_CITY")
	private City city;

}