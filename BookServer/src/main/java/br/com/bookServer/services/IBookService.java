package br.com.bookServer.services;

import java.util.List;

import br.com.bookServer.dtos.BookDTO;
import br.com.bookServer.entitys.Book;
import br.com.bookServer.exception.BookException;

public interface IBookService {

	public Book registerBook(BookDTO bookDto) throws BookException;

	public List<Book> searchAllBooks() throws BookException;

	public Book searchBookByTitle(String title) throws BookException;

	public Book searchBookById(Long bookId) throws BookException;

	public Book updateBook(BookDTO bookDto) throws BookException;

	public void deleteBook(String title) throws BookException;

}