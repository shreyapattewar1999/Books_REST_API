package com.api.book.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.book.createRequest.BookCreateRequest;
import com.api.book.entities.Book;
import com.api.book.exception.CustomException;
import com.api.book.response.ApiResponse;
import com.api.book.services.BookService;

//@RestController is the combination of @Controller and @ResponseBody annotation.
@CrossOrigin(origins = { "http://localhost:4200", "https://staging.example.com",
		"https://app.example.com" }, methods = { RequestMethod.OPTIONS, RequestMethod.GET, RequestMethod.PUT,
				RequestMethod.DELETE, RequestMethod.POST, RequestMethod.PATCH })
@RestController
@RequestMapping("/api/books")
public class BookController {

	Logger logger = LoggerFactory.getLogger(BookController.class);

	@Autowired
	private BookService bookService;

//	ResponseEntity represents the whole HTTP response: status code, headers, and body. As a result, we can use it to fully configure the HTTP response.
//	ResponseEntity is used when you need to change HTTP headers or HTTP status code based upon your business logic or incoming request. ResponseEntity wraps the original object as its body which is optional.
	@GetMapping("/")
	public ResponseEntity<List<Book>> getBooks() {
		List<Book> books = bookService.getBooks();
		logger.info("getAll request received");
		logger.debug("getAll request received DEBUG");

		if (books.size() == 0) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}

		return ResponseEntity.of(Optional.of(books));
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable("id") String bookId) {
		try {
			int bid = Integer.parseInt(bookId);

			Book newBook = null;
			newBook = bookService.getBookById(bid);
			return ResponseEntity.of(Optional.of(newBook));
		} catch (Exception e) {
			throw new CustomException("Please pass valid book id");
		}

//		if (newBook == null) {
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//		}

	}

	@PostMapping(value = "/")
	public ApiResponse addBook(@RequestBody BookCreateRequest newBook) {
		try {
			System.out.println("request received");
			this.bookService.addNewBook(newBook);
			ApiResponse response = new ApiResponse();
			response.setMessage("Book added");
			return response;
//			return ResponseEntity.accepted().body("book added");
//		return ResponseEntity.ok().build();
		} catch (Exception e) {
			throw new CustomException("Bad Request");
		}
	}

	@PutMapping("/{id}")
	public ApiResponse updateBook(@RequestBody Book bookData, @PathVariable("id") String bookId) {
		try {
			logger.info("update end point");
			int bid = Integer.parseInt(bookId);
			this.bookService.updateBook(bookData, bid);
			ApiResponse response = new ApiResponse();
			response.setMessage("Book has been updated");
			response.setStatus(true);
			return response;
//			return ResponseEntity.accepted().body("Book updated");
		} catch (Exception e) {
			throw new CustomException("Please pass valid book id");
		}

	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<List<Book>> deleteBook(@PathVariable("id") String bookId) {
		List<Book> updatedBooks = this.bookService.deleteBook(bookId);
		return ResponseEntity.of(Optional.of(updatedBooks));
//		try {
//			List<Book> updatedBooks = this.bookService.deleteBook(bookId);
//			return updatedBooks;
//		} catch (Exception e) {
//			throw new CustomException("Please pass valid book id");
//		}

	}

	@PutMapping("/{id}/updateBookName")
	public ResponseEntity<List<Book>> updateBookName(@PathVariable("id") String bookId, @RequestParam String bookName) {
		try {
			int bid = Integer.parseInt(bookId);
			System.out.print(Integer.toString(bid));
			List<Book> updatedBooks = this.bookService.updateBookName(bid, bookName);
			return ResponseEntity.of(Optional.of(updatedBooks));
		} catch (Exception e) {
			System.out.print(e);
			throw new CustomException("Please pass valid book id");
		}

	}

	@GetMapping("/booksByAuthorName/{authorName}")
	public List<Book> getBooksByAuthorName(@PathVariable("authorName") String authorName) {
		List<Book> books = this.bookService.getBooksListByAuthorName(authorName);

		return books;
	}

	@GetMapping("/booksByPageNumber")
	public List<Book> getBooksByPagination(@RequestParam String pageNo, @RequestParam String pageSize) {
		try {
			int pgNo = Integer.parseInt(pageNo);
			int pgSize = Integer.parseInt(pageSize);
			List<Book> books = this.bookService.getBooksByPageNumber(pgNo, pgSize);
			return books;
		} catch (Exception e) {
			throw new CustomException("Please pass valid page no and page size");
		}
	}

	@GetMapping("/recordsInSortedOrder")
	public List<Book> getRecordsInSortedOrder(@RequestParam(defaultValue = "bookId") String fieldName,
			@RequestParam(defaultValue = "asc") String sortOrder) {
		List<Book> books = this.bookService.getRecordsInSortedOrder(sortOrder, fieldName);
		return books;
	}

	@PutMapping("/issueBook/{id}/{userId}")
	public ResponseEntity<String> issueBook(@PathVariable("id") String bookId, @PathVariable("userId") String userId) {
		this.bookService.issueNewBook(Integer.parseInt(bookId), Integer.parseInt(userId));
		return ResponseEntity.accepted().body("book issued by user");
	}

	@PutMapping("/updateBookCopies/{userId}/{bookId}")
	public ResponseEntity<String> updateBookDetails(@PathVariable("userId") String userId,
			@PathVariable("bookId") String bookId, @RequestBody HashMap<String, String> requestData) {
		this.bookService.updateNoOfCopies(bookId, userId, requestData.get("field"), requestData.get("fieldValue"));
		return ResponseEntity.accepted().body("book updated");
	}
}
