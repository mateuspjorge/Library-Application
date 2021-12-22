package br.com.locationServer.entitys;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
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
@NoArgsConstructor
@AllArgsConstructor
public class Country implements Serializable {

	private static final long serialVersionUID = -5049507472994103076L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_COUNTRY")
	@Column(name = "ID_COUNTRY", nullable = false)
	private Long id;

	@Column(name = "DSC_NAME", unique = true, nullable = false, length = 30)
	private String name;

	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH })
	@JoinTable(
			   name = "TB_COUNTRY_STATES",
			   joinColumns = @JoinColumn(name = "ID_COUNTRY",
										 referencedColumnName = "ID_COUNTRY"),
			   inverseJoinColumns = @JoinColumn(name = "ID_STATE",
												referencedColumnName = "ID_STATE"))
	private List<State> states;

}