package com.api.book.repositories;

import org.springframework.data.repository.CrudRepository;

import com.api.book.entities.Author;

public interface AuthorRepository extends CrudRepository<Author, Integer> {

}
