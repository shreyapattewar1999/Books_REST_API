package com.api.book.repositories;

import org.springframework.data.repository.CrudRepository;

import com.api.book.entities.Address;

public interface AddressRepository extends CrudRepository<Address, Integer> {

}
