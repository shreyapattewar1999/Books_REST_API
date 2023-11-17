package com.api.book.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.api.book.entities.Book;

public interface BookRepository extends CrudRepository<Book, String> {

	public Book findByBookId(String bookId);

	@Query("select b from Book b where b.author in ( select a.authorId from Author a where a.firstName like %?1% or a.lastName like %?1%)")
	public List<Book> findBooksByAuthorName(String authorName);

	public Page<Book> findAll(Pageable pageable);

	public List<Book> findAll(Sort sort);
}
