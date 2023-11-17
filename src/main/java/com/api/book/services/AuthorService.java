package com.api.book.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.book.createRequest.AuthorCreateRequest;
import com.api.book.entities.Address;
import com.api.book.entities.Author;
import com.api.book.repositories.AddressRepository;
import com.api.book.repositories.AuthorRepository;

@Service
public class AuthorService {

	@Autowired
	public AuthorRepository authorRepository;

	@Autowired
	public AddressRepository addressRepository;

	public void addAuthor(AuthorCreateRequest authorRequestObject) {

		Author author = new Author(authorRequestObject.getAuthorFirstName(), authorRequestObject.getAuthorLastName());

		Address address = new Address(authorRequestObject.getStreet(), authorRequestObject.getDistrict(),
				authorRequestObject.getState(), authorRequestObject.getPostalCode(), authorRequestObject.getEmail(),
				authorRequestObject.getContactNo());

		address = addressRepository.save(address);

		author.setAddress(address);

		authorRepository.save(author);

	}

}
