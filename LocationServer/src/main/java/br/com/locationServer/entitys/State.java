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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
@Table(name = "TB_STATE")
@GenericGenerator(
        name = "SEQ_STATE",
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = {
                @Parameter(name = "sequence_name", value = "SEQ_STATE"),
                @Parameter(name = "initial_value", value = "1"),
                @Parameter(name = "increment_size", value = "1")
        }
)
public class State implements Serializable {

	private static final long serialVersionUID = 7877845558420996364L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_STATE")
	@Column(name = "ID_STATE", nullable = false)
	private Long id;

	@Column(name = "DSC_NAME", nullable = false)
	private String name;

	@Column(name = "DSC_INITIALS", nullable = false, length = 2)
	private String initials;

	@ManyToOne
	@JoinColumn(name = "ID_COUNTRY")
	@Column(name = "ID_COUNTRY", nullable = false)
	private Country country;

	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH })
	@JoinTable(
			   name = "TB_CITIES_STATE",
			   joinColumns = @JoinColumn(name = "ID_STATE",
										 referencedColumnName = "ID_STATE"),
			   inverseJoinColumns = @JoinColumn(name = "ID_CITY",
												referencedColumnName = "ID_CITY"))
	private List<City> cities;

}