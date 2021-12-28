package br.com.bookServer.dtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

import br.com.bookServer.entitys.Book;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookDTO implements Serializable {

	private static final long serialVersionUID = -3843471000720834286L;

	private Long id;

	private String title;

	private String newTitle;

	private String summary;

	private String newSummary;

	private String author;

	private String newAuthor;

	private String isbn;

	private String newIsbn;

	private String messageError;

	private Integer year;

	private Integer newYear;

	public static BookDTO createDtoWithMessageError(String error) {
		return BookDTO.builder().messageError(error).build();
	}

	public static BookDTO convertBookToDto(Book book) {
		return BookDTO.builder()
					  .id(book.getId())
					  .title(book.getTitle())
					  .summary(book.getSummary())
					  .author(book.getAuthor())
					  .isbn(book.getIsbn())
					  .year(book.getYear())
					  .build();
	}

	public static Book convertDtoToBook(BookDTO bookDto) {
		return Book.builder()
				   .id(bookDto.getId())
				   .title(bookDto.getTitle())
				   .summary(bookDto.getSummary())
				   .author(bookDto.getAuthor())
				   .isbn(bookDto.getIsbn())
				   .year(bookDto.getYear())
				   .build();
	}

	public static List<BookDTO> convertListBookToListDto(List<Book> listBook) {
		List<BookDTO> bookDtos = new ArrayList<>();
		if (!CollectionUtils.isEmpty(listBook)) {
			listBook.forEach(book -> bookDtos.add(convertBookToDto(book)));
		}
		return bookDtos;
	}

	public static List<Book> convertListDtoToListBook(List<BookDTO> bookDtos) {
		List<Book> books = new ArrayList<>();
		if (!CollectionUtils.isEmpty(bookDtos)) {
			bookDtos.forEach(dto -> books.add(convertDtoToBook(dto)));
		}
		return books;
	}

}