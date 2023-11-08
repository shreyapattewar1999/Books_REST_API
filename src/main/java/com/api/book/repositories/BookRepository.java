package com.api.book.repositories;

import org.springframework.data.repository.CrudRepository;

import com.api.book.entities.Book;



public interface BookRepository  extends CrudRepository<Book, String> {
	
	public Book findByBookId(String bookId);
}
