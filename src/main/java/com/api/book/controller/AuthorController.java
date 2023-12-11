package com.api.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.book.createRequest.AuthorCreateRequest;
import com.api.book.services.AuthorService;

@RestController
@RequestMapping("/api/author")
public class AuthorController {

	@Autowired
	private AuthorService authorService;

	@PostMapping("/")
	public ResponseEntity<String> addAuthor(@RequestBody AuthorCreateRequest authorCreateRequestObject) {
		try {
			this.authorService.addAuthor(authorCreateRequestObject);
			return ResponseEntity.accepted().body("author added");

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

		}
	}

}
