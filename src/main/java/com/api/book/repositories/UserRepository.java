package com.api.book.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.api.book.entities.User;

public interface UserRepository extends CrudRepository<User, Integer> {

	public User findByUserId(int userId);

	public User findByEmailAddress(String email);

	public List<User> findByFirstNameAndLastName(String firstName, String lastName);

	public void deleteByEmailAddress(String emailAddress);
}
