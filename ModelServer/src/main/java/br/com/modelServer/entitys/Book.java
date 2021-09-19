package br.com.modelServer.entitys;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Book {

	private Long id;

	private String title;

	private String summary;

	private String isbn;

	private String author;

	private int year;

}