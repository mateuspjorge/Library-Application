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
@Table(name = "TB_CITY")
@GenericGenerator(
        name = "SEQ_CITY",
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = {
                @Parameter(name = "sequence_name", value = "SEQ_CITY"),
                @Parameter(name = "initial_value", value = "1"),
                @Parameter(name = "increment_size", value = "1")
        }
)
public class City implements Serializable {

	private static final long serialVersionUID = -4943982364577103356L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CITY")
	@Column(name = "ID_CITY", nullable = false)
	private Long id;

	@Column(name = "DSC_NAME", unique = true, nullable = false)
	private String name;

	@ManyToOne
	@JoinColumn(name = "ID_STATE")
	private State state;

	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH })
	@JoinTable(
			   name = "TB_CITY_ADDRESSES",
			   joinColumns = @JoinColumn(name = "ID_CITY",
										 referencedColumnName = "ID_CITY"),
			   inverseJoinColumns = @JoinColumn(name = "ID_ADDRESS",
												referencedColumnName = "ID_ADDRESS"))
	private List<Address> addresses;

}