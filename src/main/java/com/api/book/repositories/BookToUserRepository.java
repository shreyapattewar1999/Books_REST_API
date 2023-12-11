package com.api.book.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.api.book.entities.BookToUser;

public interface BookToUserRepository extends CrudRepository<BookToUser, Integer> {

	List<BookToUser> findByBookIdAndUserId(int bookId, int userId);
}
