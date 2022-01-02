package br.com.userServer.entitys;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "TB_USER")
@GenericGenerator(
        name = "SEQ_USER",
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = {
                @Parameter(name = "sequence_name", value = "SEQ_USER"),
                @Parameter(name = "initial_value", value = "1"),
                @Parameter(name = "increment_size", value = "1")
        }
)
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

	private static final long serialVersionUID = -2738355686108408182L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USER")
	@Column(name = "ID_USER", nullable = false)
	private Long id;

	@Column(name = "ID_GOOGLE")
	private String uId;

	@Column(name = "DSC_LOGIN", nullable = false, unique = true)
	private String username;

	@Column(name = "DSC_SENHA", nullable = false)
	private String password;

	@Column(name = "DSC_EMAIL", nullable = false, unique = true)
	private String email;

}