package br.com.loanServer.entitys;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
@Table(name = "TB_LOAN")
@GenericGenerator(
        name = "SEQ_LOAN",
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = {
                @Parameter(name = "sequence_name", value = "SEQ_LOAN"),
                @Parameter(name = "initial_value", value = "1"),
                @Parameter(name = "increment_size", value = "1")
        }
)
public class Loan {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_LOAN")
	@Column(name = "ID_LOAN", nullable = false)
	private Long id;

	@Column(name = "ID_USER", nullable = false)
	private Long userId;

	@Column(name = "ID_BOOK", nullable = false)
	private Long bookId;

	@Column(name = "DT_START", nullable = false)
	@Temporal(value = TemporalType.DATE)
	private Calendar startDate;

	@Column(name = "DT_END")
	@Temporal(value = TemporalType.DATE)
	private Calendar endDate;

}