package com.api.book.services;

//import java.util.ArrayList;
import java.util.List;
//import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.book.entities.Book;
import com.api.book.repositories.BookRepository;

@Service
public class BookService {

	@Autowired
	public BookRepository bookRepository;

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

	public Book getBookById(String incomingBookId) {
		try {
			Book newBook = null;
//		newBook= booksList.stream().filter(b->b.getBookId().equals(incomingBookId)).findFirst().orElse(null);
			newBook = bookRepository.findByBookId(incomingBookId);
			return newBook;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void addNewBook(Book incomingBook) {
		try {
//		booksList.add(incomingBook);
			bookRepository.save(incomingBook);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateBook(Book bookData, String bookId) {
//		booksList.forEach(b->{
//			if(b.getBookId().equals(bookId)) {
//				b.setBookId(bookData.getBookId());
//				b.setBookname(bookData.getBookname());
//				b.setAuthor(bookData.getAuthor());
//			}
//		});

//		.save() --> if incoming book data's ID is not available in books table, new book is saved, 
//		if incoming book's ID is present in books table, then it updates existing row
		bookRepository.save(bookData);

	}

	public List<Book> deleteBook(String bookId) {
		bookRepository.deleteById(bookId);
		List<Book> updatedBooks = (List<Book>) bookRepository.findAll();
//		booksList = booksList.stream().filter(b-> ! b.getBookId().equals(bookId)).collect(Collectors.toList());
		return updatedBooks;
	}

	public List<Book> updateBookName(String bookId, String bookName) {
		Book bookToBeUpdated = bookRepository.findByBookId(bookId);
		bookToBeUpdated.setTitle(bookName);
		bookRepository.save(bookToBeUpdated);

		List<Book> updatedBooks = (List<Book>) bookRepository.findAll();
		return updatedBooks;
	}
}
