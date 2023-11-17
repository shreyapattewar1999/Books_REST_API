package com.api.book.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "addresses")
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

	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Address(String street, String district, String state, int postalCode, String emailAddress,
			String contactNumber) {
		super();
//		this.addressId = addressId;
		this.street = street;
		this.district = district;
		this.state = state;
		this.postalCode = postalCode;
		this.emailAddress = emailAddress;
		this.contactNumber = contactNumber;
	}

	@Override
	public String toString() {
		return "Address [addressId=" + addressId + ", street=" + street + ", district=" + district + ", state=" + state
				+ ", postalCode=" + postalCode + ", emailAddress=" + emailAddress + ", contactNumber=" + contactNumber
				+ "]";
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

}
