package com.api.book.createRequest;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookCreateRequest {
	public String bookTitle;
	public String genre;
	public List<String> languages;
	public String publisher;
	public String publishedYear;
	public String description;
	public float rating;
	public String authorFirstName;
	public String authorLastName;
	public String street;
	public String district;
	public String state;
	public int postalCode;
	public String email;
	public String contactNo;
	public int noOfPages;
	public int noOfCopies;
	public int userId;

	public BookCreateRequest() {
		// TODO Auto-generated constructor stub
	}

}
