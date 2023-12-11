package com.api.book.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class BookToUser {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private int bookId;
	private int userId;

	public BookToUser(int bookId, int userId) {
		super();
		this.bookId = bookId;
		this.userId = userId;
		// TODO Auto-generated constructor stub
	}

}
