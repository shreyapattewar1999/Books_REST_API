package com.api.book.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.book.entities.Book;
import com.api.book.services.BookService;

//@RestController is the combination of @Controller and @ResponseBody annotation.
@RestController
public class BookController {

	@Autowired
	private BookService bookService;

//	ResponseEntity represents the whole HTTP response: status code, headers, and body. As a result, we can use it to fully configure the HTTP response.
//	ResponseEntity is used when you need to change HTTP headers or HTTP status code based upon your business logic or incoming request. ResponseEntity wraps the original object as its body which is optional.
	@GetMapping("/books")
	public ResponseEntity<List<Book>> getBooks() {
		List<Book> books = bookService.getBooks();
		System.out.println("request received");
		if (books.size() == 0) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}

		return ResponseEntity.of(Optional.of(books));
	}

	@GetMapping(value = "/books/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable("id") String bookId) {
		Book newBook = null;
		newBook = bookService.getBookById(bookId);
		if (newBook == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(newBook));
	}

	@PostMapping(value = "/books")
	public ResponseEntity<String> addBook(@RequestBody Book newBook) {
		try {
			System.out.println("request received");
			this.bookService.addNewBook(newBook);
			return ResponseEntity.accepted().body("book added");
//		return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@PutMapping("/books/{id}")
	public void updateBook(@RequestBody Book bookData, @PathVariable("id") String bookId) {
		this.bookService.updateBook(bookData, bookId);
	}

	@DeleteMapping("/books/{id}")
	public List<Book> deleteBook(@PathVariable("id") String bookId) {
		List<Book> updatedBooks = this.bookService.deleteBook(bookId);
		return updatedBooks;
	}

	@PatchMapping("/books/{id}/updateName")
	public List<Book> updateBookName(@PathVariable("id") String bookId, @RequestParam String bookName) {
		List<Book> updatedBooks = this.bookService.updateBookName(bookId, bookName);
		return updatedBooks;
	}

}
