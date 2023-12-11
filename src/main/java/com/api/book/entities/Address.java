package com.api.book.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "addresses")
@NoArgsConstructor
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int addressId;

	private String street;

	private String district;

	private String state;

	private int postalCode;

	@Column(name = "email")
	private String emailAddress;

	@Column(name = "contact_number")
	private String contactNumber;

	public Address(String street, String district, String state, int postalCode, String emailAddress,
			String contactNumber) {
		super();
		this.street = street;
		this.district = district;
		this.state = state;
		this.postalCode = postalCode;
		this.emailAddress = emailAddress;
		this.contactNumber = contactNumber;
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Address [addressId=" + addressId + ", street=" + street + ", district=" + district + ", state=" + state
				+ ", postalCode=" + postalCode + ", emailAddress=" + emailAddress + ", contactNumber=" + contactNumber
				+ "]";
	}

}
