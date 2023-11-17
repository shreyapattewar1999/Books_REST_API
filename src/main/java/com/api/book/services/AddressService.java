package com.api.book.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.book.repositories.AddressRepository;

@Service
public class AddressService {

	@Autowired
	public AddressRepository addressRepository;

	public void addAddress() {

	}
}
