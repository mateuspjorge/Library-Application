package br.com.modelServer.entitys;

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
@Table(name = "TB_BOOK")
@GenericGenerator(
        name = "SEQ_BOOK",
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = {
                @Parameter(name = "sequence_name", value = "SEQ_BOOK"),
                @Parameter(name = "initial_value", value = "1"),
                @Parameter(name = "increment_size", value = "1")
        }
)
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_BOOK")
	@Column(name = "ID_BOOK")
	private Long id;

	@Column(name = "DS_TITLE", nullable = false)
	private String title;

	@Column(name = "DS_SUMMARY", nullable = false)
	private String summary;

	@Column(name = "NM_AUTHOR", nullable = false)
	private String author;

	@Column(name = "NR_ISBN", nullable = false)
	private String isbn;

	@Column(name = "NR_YEAR", nullable = false)
	private Integer year;

}