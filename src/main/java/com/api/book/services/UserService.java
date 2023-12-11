package com.api.book.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.book.createRequest.UserRequest;
import com.api.book.entities.BookToUser;
import com.api.book.entities.User;
import com.api.book.exception.CustomException;
import com.api.book.repositories.BookToUserRepository;
import com.api.book.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	public UserRepository userRepository;

	@Autowired
	public BookToUserRepository bookToUserRepository;

	public UserService() {
		// TODO Auto-generated constructor stub
	}

	public List<User> getUsers() {
		// TODO Auto-generated method stub
		List<User> users = (List<User>) userRepository.findAll();
		return users;
	}

	public void addNewUser(UserRequest userRequest) {
		try {
//			Address address = new Address(userRequest.street(), userRequest.district(), userRequest.state(),
//					userRequest.postalCode(), userRequest.emailAddress(), userRequest.contactNumber());
			User u = new User(userRequest.firstName(), userRequest.lastName(), userRequest.password(),
					userRequest.isAdmin(), userRequest.emailAddress());
			userRepository.save(u);
		} catch (Exception ex) {
			throw new CustomException("User cannot be saved");
		}
	}

	public User verifyUserCredentials(UserRequest userData) {
		User u = new User();
		try {
			u = userRepository.findByEmailAddress(userData.emailAddress());
			if (u.getPassword().equals(userData.password())) {
				return u;
			} else {
				throw new CustomException("Incorrect Credentials");
			}
		} catch (Exception ex) {
			throw new CustomException("User not found");
		}
	}

	public void updateUserDetails(UserRequest userData) {
		try {
			List<User> users = userRepository.findByFirstNameAndLastName(userData.firstName(), userData.lastName());
			users.get(0).setEmailAddress(userData.emailAddress());
			users.get(0).setPassword(userData.password());
			userRepository.save(users.get(0));
		} catch (Exception ex) {
			throw new CustomException("User details can not be updated");
		}
	}

	public void deleteUser(String email) {
		try {
			userRepository.deleteByEmailAddress(email);
		} catch (Exception ex) {
			throw new CustomException("User details can not be updated");
		}
	}

	public String issueBook(String userId, String bookId) {

		try {
			List<BookToUser> issuedBooks = bookToUserRepository.findByBookIdAndUserId(Integer.parseInt(bookId),
					Integer.parseInt(userId));
			if (issuedBooks.size() > 0) {
				return "You have already issued this book";
			}
			BookToUser newBookIssue = new BookToUser(Integer.parseInt(bookId), Integer.parseInt(userId));
			bookToUserRepository.save(newBookIssue);
			return "Book has been issued";
		} catch (Exception ex) {
			throw new CustomException("User details can not be updated");
		}
	}
}
