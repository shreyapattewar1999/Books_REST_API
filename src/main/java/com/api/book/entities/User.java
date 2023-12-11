package com.api.book.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "userId")
	private int userId;

	private String firstName;

	private String lastName;

	private String password;

	private String emailAddress;

	private Boolean isAdmin;

//	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(referencedColumnName = "addressId")
//	private Address address;

	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(String fname, String lname, String password, Boolean isAdmin, String email_address) {
		super();
		this.firstName = fname;
		this.lastName = lname;
		this.password = password;
		this.isAdmin = isAdmin;
		this.emailAddress = email_address;
//		this.address = address;
	}

}
