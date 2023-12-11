package com.api.book.services;

//import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.api.book.createRequest.BookCreateRequest;
import com.api.book.entities.Address;
import com.api.book.entities.Author;
import com.api.book.entities.Book;
import com.api.book.entities.BookToUser;
import com.api.book.exception.CustomException;
import com.api.book.exception.ResourceNotFoundException;
import com.api.book.repositories.BookRepository;
import com.api.book.repositories.BookToUserRepository;

@Service
public class BookService {

	@Autowired
	public BookRepository bookRepository;

	@Autowired
	public BookToUserRepository bookUserRepository;

//	private static List<Book> booksList = new ArrayList<>();
//	
//	static {
//		booksList.add(new Book("12", "Atomic Habits", "James Clear"));
//		booksList.add(new Book("25", "Three Thousand Stiches", "Sudha Murthy"));
//		booksList.add(new Book("36", "Do Epic Shit", "Ankoor Warikoo"));
//		
//	}

	public List<Book> getBooks() {
		List<Book> books = (List<Book>) bookRepository.findAll();
		return books;
	}

	public Book getBookById(int bid) {
//		newBook= booksList.stream().filter(b->b.getBookId().equals(incomingBookId)).findFirst().orElse(null);
		Book book = bookRepository.findByBookId(bid);
		if (book != null) {
			return book;

		} else {
			throw new ResourceNotFoundException("Book", "bookId", Integer.toString(bid));
		}
	}

	public void addNewBook(BookCreateRequest incomingBook) {
		try {
			Address address = new Address(incomingBook.street, incomingBook.district, incomingBook.state,
					incomingBook.postalCode, incomingBook.email, incomingBook.contactNo);
			Author author = new Author(incomingBook.getAuthorFirstName(), incomingBook.getAuthorLastName(), address);
			Book book = new Book(incomingBook.bookTitle, incomingBook.publishedYear, incomingBook.languages,
					incomingBook.publisher, incomingBook.genre, incomingBook.description, incomingBook.rating,
					incomingBook.noOfPages, incomingBook.noOfCopies, author);

			bookRepository.save(book);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CustomException("Book could not be updated, Please submit valid book details");

		}
	}

	public void updateBook(Book bookData, int bid) {
//		booksList.forEach(b->{
//			if(b.getBookId().equals(bookId)) {
//				b.setBookId(bookData.getBookId());
//				b.setBookname(bookData.getBookname());
//				b.setAuthor(bookData.getAuthor());
//			}
//		});

//		.save() --> if incoming book data's ID is not available in books table, new book is saved, 
//		if incoming book's ID is present in books table, then it updates existing row
		try {
			bookRepository.save(bookData);
		} catch (Exception e) {
			throw new CustomException("Book could not be updated, Please submit valid book details");
		}

	}

	public List<Book> deleteBook(String bookId) {
		try {
			int bid = Integer.parseInt(bookId);
			Book book = bookRepository.findByBookId(bid);
			System.out.println(book != null);
			if (book != null) {
				bookRepository.deleteById(bid);
//				bookRepository.deleteBooks(bid);
				List<Book> updatedBooks = (List<Book>) bookRepository.findAll();
				return updatedBooks;
			} else {
				throw new ResourceNotFoundException("Book", "bookId", Integer.toString(bid));
			}
		} catch (Exception ex) {
			throw new CustomException(ex.getMessage());
//			throw new ResourceNotFoundException("Book", "bookId", bookId);

//			throw new CustomException("Please pass valid book id");
		}

	}

	public List<Book> updateBookName(int bid, String bookName) {
		Book bookToBeUpdated = bookRepository.findByBookId(bid);
		if (bookToBeUpdated == null) {
			throw new ResourceNotFoundException("Book", "bookId", Integer.toString(bid));
		} else {

			bookToBeUpdated.setTitle(bookName);
			bookRepository.save(bookToBeUpdated);

			List<Book> updatedBooks = (List<Book>) bookRepository.findAll();
			return updatedBooks;
		}

	}

	public List<Book> getBooksListByAuthorName(String authorName) {
		List<Book> books = this.bookRepository.findBooksByAuthorName(authorName);
		if (books.size() == 0) {
			throw new CustomException("This author does not have any books");
		}
		return books;
	}

	public List<Book> getBooksByPageNumber(int pageNo, int pageSize) {
		try {
			Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
			return bookRepository.findAll(pageable).getContent();
		} catch (Exception ex) {
			throw new CustomException("Invalid Data");
		}
	}

	public List<Book> getRecordsInSortedOrder(String sortOrder, String fieldName) {
		Sort sort = Sort.by((sortOrder.equals("asc")) ? Sort.Direction.ASC : Sort.Direction.DESC, fieldName);
		return bookRepository.findAll(sort);
	}

	public void issueNewBook(int bookId, int userId) {
		try {
			Book bookToBeUpdated = bookRepository.findByBookId(bookId);
			if (bookToBeUpdated.getNoOfCopies() == 0) {
				throw new CustomException("This book is out of stock");
			}
			bookToBeUpdated.setNoOfCopies(bookToBeUpdated.getNoOfCopies() - 1);
			bookRepository.save(bookToBeUpdated);
			BookToUser newBookIssue = new BookToUser(bookId, userId);
			bookUserRepository.save(newBookIssue);
		} catch (Exception ex) {
			throw new CustomException("This book cannot be issued, please try again later");
		}
	}

	public void updateNoOfCopies(String bid, String uid, String field, String fieldValue) {
		int bookId = Integer.parseInt(bid);
		int userId = Integer.parseInt(uid);
		// TODO Auto-generated method stub
		Book bookToBeUpdated = bookRepository.findByBookId(bookId);
		if (field.equalsIgnoreCase("rating")) {
			bookToBeUpdated.setRating(Float.parseFloat(fieldValue));
		}
//		if(field.equalsIgnoreCase("noOfPages")
//		bookToBeUpdated.setNoOfCopies(noOfCopies);
		bookRepository.save(bookToBeUpdated);
	}
}
