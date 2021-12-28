package br.com.bookServer.services.impl;

import java.util.List;
import java.util.Objects;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import br.com.bookServer.dtos.BookDTO;
import br.com.bookServer.entitys.Book;
import br.com.bookServer.exception.BookException;
import br.com.bookServer.repositorys.IBookRepository;
import br.com.bookServer.services.IBookService;

@Service
public class BookServiceImpl implements IBookService {

	private static final String MSG_ERROR_NENHUM_REGISTRO_ENCONTRADO = "Nenhum registro foi encontrado.";

	private IBookRepository bookRepository;

	@Autowired
	public BookServiceImpl(IBookRepository bookRepository) {
		super();
		this.bookRepository = bookRepository;
	}

	@Override
	public Book registerBook(BookDTO bookDto) throws BookException {
		validateBook(bookDto);
		return bookRepository.save(BookDTO.convertDtoToBook(bookDto));
	}

	@Override
	public List<Book> searchAllBooks() throws BookException {
		List<Book> booksFound = bookRepository.findAll();
		if (CollectionUtils.isEmpty(booksFound)) {
			throw new BookException(MSG_ERROR_NENHUM_REGISTRO_ENCONTRADO);
		}
		return booksFound;
	}

	@Override
	public Book searchBookByTitle(String title) throws BookException {
		if (StringUtils.isBlank(title)) {
			throw new BookException("O título informado é inválido.");
		}
		Book bookFound = bookRepository.findByTitle(title.toUpperCase());
		if (Objects.isNull(bookFound)) {
			throw new BookException(MSG_ERROR_NENHUM_REGISTRO_ENCONTRADO);
		}
		return bookFound;
	}

	@Override
	public Book searchBookById(Long bookId) throws BookException {
		Book bookFound = bookRepository.findById(bookId).orElse(null);
		if (Objects.isNull(bookFound)) {
			throw new BookException(MSG_ERROR_NENHUM_REGISTRO_ENCONTRADO);
		}
		return bookFound;
	}

	@Override
	public Book updateBook(BookDTO bookDto) throws BookException {
		Book bookFound = searchBookByTitle(bookDto.getTitle());
		if (StringUtils.isBlank(bookDto.getNewTitle())) {
			bookDto.setTitle(bookDto.getNewTitle());
		}
		if (StringUtils.isBlank(bookDto.getNewSummary())) {
			bookDto.setSummary(bookDto.getNewSummary());
		}
		if (StringUtils.isBlank(bookDto.getNewAuthor())) {
			bookDto.setAuthor(bookDto.getNewAuthor());
		}
		if (StringUtils.isBlank(bookDto.getNewIsbn())) {
			bookDto.setIsbn(bookDto.getNewIsbn());
		}
		if (Objects.isNull(bookDto.getNewYear())) {
			bookDto.setYear(bookDto.getNewYear());
		}
		return bookRepository.save(bookFound);
	}

	@Override
	public void deleteBook(String title) throws BookException {
		bookRepository.delete(searchBookByTitle(title));
	}

	protected void validateBook(BookDTO bookDto) throws BookException {
		if (StringUtils.isBlank(bookDto.getTitle())) {
			throw new BookException("O título informado é inválido.");
		}
		if (StringUtils.isBlank(bookDto.getSummary())) {
			throw new BookException("O resumo informado é inválido.");
		}
		if (StringUtils.isBlank(bookDto.getAuthor())) {
			throw new BookException("O Autor informado é inválido.");
		}
		if (StringUtils.isBlank(bookDto.getIsbn())) {
			throw new BookException("O ISBN informado é inválido.");
		}
		if (Objects.isNull(bookDto.getYear())) {
			throw new BookException("O Ano informado é inválido.");
		}
	}

}