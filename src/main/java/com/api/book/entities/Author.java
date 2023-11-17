package com.api.book.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "authors")
public class Author {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int authorId;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

//	@Transient
//	private String fullName;

//	@OneToMany(mappedBy = "author")
//	private List<Book> writtenBooks;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(referencedColumnName = "addressId")
	private Address address;

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Author() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Author(String firstName, String lastName) {
		super();
//		this.authorId = authorId;
		this.firstName = firstName;
		this.lastName = lastName;
//		this.fullName = firstName + " " + lastName;
	}

	@Override
	public String toString() {
		return "Author [authorId =" + authorId + ", fullName = " + " ]";
	}

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
