package com.api.book.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.book.entities.Book;
import com.api.book.services.BookService;

@RestController
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@GetMapping("/books")
	public ResponseEntity<List<Book>> getBooks() {
		List<Book> books = bookService.getBooks();
		if (books.size() == 0) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		
		return ResponseEntity.of(Optional.of(books));
	}
	
	@GetMapping("/books/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable("id") String bookId) {
		Book newBook =  null;
		newBook =  bookService.getBookById(bookId);
		if (newBook == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(newBook));
	}
	
	@PostMapping("/books")
	public ResponseEntity<Book> addBook(@RequestBody Book newBook) {
		try {
		this.bookService.addNewBook(newBook);
		return ResponseEntity.ok().build();
		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@PutMapping("/books/{id}")
	public void updateBook(@RequestBody Book bookData, @PathVariable("id") String bookId) {
		this.bookService.updateBook(bookData, bookId);
	}
	
	@DeleteMapping("/books/{id}")
	public List<Book> deleteBook(@PathVariable("id") String bookId){
		List<Book> updatedBooks = this.bookService.deleteBook(bookId);
		return updatedBooks;
	}
	
}
