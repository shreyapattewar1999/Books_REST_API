package com.api.book.createRequest;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserRequest(@JsonProperty("firstName") String firstName, @JsonProperty("lastName") String lastName,
		@JsonProperty("password") String password, @JsonProperty("isAdmin") Boolean isAdmin,
		@JsonProperty("street") String street, @JsonProperty("district") String district,
		@JsonProperty("state") String state, @JsonProperty("postalCode") int postalCode,
		@JsonProperty("emailAddress") String emailAddress, @JsonProperty("contactNumber") String contactNumber) {
}

//public class UserRequest {
//
//	public UserRequest() {
//		// TODO Auto-generated constructor stub
//	}
//
//}
