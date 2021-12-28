package br.com.bookServer.controllers.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.bookServer.controllers.BookController;
import br.com.bookServer.dtos.BookDTO;
import br.com.bookServer.exception.BookException;
import br.com.bookServer.services.IBookService;

@RestController
@RequestMapping(path = "/book")
public class BookControllerImpl implements BookController {

	private IBookService bookService;

	@Autowired
	public BookControllerImpl(IBookService bookService) {
		super();
		this.bookService = bookService;
	}

	@PostMapping("/register")
	@ResponseBody
	@Override
	public ResponseEntity<BookDTO> registerBook(BookDTO bookDto) {
		try {
			return ResponseEntity.ok(BookDTO.convertBookToDto(bookService.registerBook(bookDto)));
		} catch (BookException exception) {
			exception.printStackTrace();
			return ResponseEntity.status(406).body(BookDTO.createDtoWithMessageError(exception.getMessage()));
		}
	}

	@GetMapping("/search")
	@ResponseBody
	@Override
	public ResponseEntity<List<BookDTO>> searchAllBooks() {
		try {
			return ResponseEntity.ok(BookDTO.convertListBookToListDto(bookService.searchAllBooks()));
		} catch (BookException exception) {
			exception.printStackTrace();
			return ResponseEntity.status(204).body(Arrays.asList(BookDTO.createDtoWithMessageError(exception.getMessage())));
		}
	}

	@GetMapping("/internal/search-by/{bookId}")
	@ResponseBody
	@Override
	public ResponseEntity<BookDTO> searchBookById(Long bookId) {
		try {
			return ResponseEntity.ok(BookDTO.convertBookToDto(bookService.searchBookById(bookId)));
		} catch (BookException exception) {
			exception.printStackTrace();
			return ResponseEntity.status(204).body(BookDTO.createDtoWithMessageError(exception.getMessage()));
		}
	}

	@PutMapping("/update")
	@ResponseBody
	@Override
	public ResponseEntity<BookDTO> updateBook(BookDTO bookDto) {
		try {
			return ResponseEntity.ok(BookDTO.convertBookToDto(bookService.updateBook(bookDto)));
		} catch (BookException exception) {
			exception.printStackTrace();
			return ResponseEntity.status(406).body(BookDTO.createDtoWithMessageError(exception.getMessage()));
		}
	}

	@DeleteMapping("/delete")
	@ResponseBody
	@Override
	public ResponseEntity<BookDTO> deleteBook(BookDTO bookDto) {
		try {
			bookService.deleteBook(bookDto.getTitle());
			return ResponseEntity.status(200).body(BookDTO.createDtoWithMessageError(null));
		} catch (BookException exception) {
			exception.printStackTrace();
			return ResponseEntity.status(406).body(BookDTO.createDtoWithMessageError(exception.getMessage()));
		}
	}

}