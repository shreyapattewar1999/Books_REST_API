package com.api.book.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.book.createRequest.UserRequest;
import com.api.book.entities.User;
import com.api.book.exception.CustomException;
import com.api.book.response.ApiResponse;
import com.api.book.services.UserService;

@RestController()
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	UserService userService;

	public UserController() {
		// TODO Auto-generated constructor stub
	}

	@GetMapping("/")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users = this.userService.getUsers();
		if (users.size() == 0) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}

		return ResponseEntity.of(Optional.of(users));
	}

	@PostMapping("/")
	public ApiResponse addNewUser(@RequestBody UserRequest userRequest) {
		ApiResponse response = new ApiResponse();
		try {
			System.out.println(userRequest);
			this.userService.addNewUser(userRequest);

			response.setMessage("User Added");
			response.setStatus(true);
			return response;

//			return ResponseEntity.accepted().body("user added");
//		return ResponseEntity.ok().build();
		} catch (Exception e) {
			return response;
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@PostMapping("/verify-user")
	public ApiResponse verifyUser(@RequestBody UserRequest userRequest) {
		ApiResponse response = new ApiResponse();
		try {
			User u = this.userService.verifyUserCredentials(userRequest);
			if (u != null) {
				response.setData(u);
			}
		} catch (Exception ex) {
			throw new CustomException("Something went wrong, please try again");
		}

		return response;
	}

	@PostMapping("/updatedetails")
	public ApiResponse updateUserDetails(@RequestBody UserRequest userRequest) {
		ApiResponse response = new ApiResponse();
		try {
			this.userService.updateUserDetails(userRequest);
			response.setMessage("User details have been updated");
			response.setStatus(true);
			return response;
		} catch (Exception ex) {
			throw new CustomException("Something went wrong, please try again");
		}
	}

	@DeleteMapping("/{email}")
	public ApiResponse deleteUserData(@PathVariable("email") String emailAddress) {
		ApiResponse response = new ApiResponse();
		try {
			this.userService.deleteUser(emailAddress);
			response.setMessage("User has been deleted");
			response.setStatus(true);
			return response;
		} catch (Exception ex) {
			throw new CustomException("Something went wrong, please try again");
		}
	}

	@PostMapping("/issueBook/{userId}/{bookId}")
	public ApiResponse issueBook(@PathVariable("userId") String userId, @PathVariable("bookId") String bookId) {
		ApiResponse response = new ApiResponse();
		try {
			String message = userService.issueBook(userId, bookId);
			response.setMessage(message);
			response.setStatus(true);
			return response;
		} catch (Exception ex) {
			throw new CustomException("Something went wrong, please try again");
		}
	}
}
