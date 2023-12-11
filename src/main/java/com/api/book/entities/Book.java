package com.api.book.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "books")
@Getter
@Setter
@ToString
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "bookId")
	private int bookId;

	@Column(name = "title")
	private String title;

	private String publishedYear;

	private List<String> languages;

	private String publisher;

	private String genre;

	private String description;

	private float rating;

	private int noOfPages;

	private int noOfCopies;

	private float reviews = 0;
	private float ratings = 0;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(referencedColumnName = "authorId")
	private Author author;
//	here book has author id as foreign key, hence we can fetch author details via book object
//	however we can't fetch book details from author 

	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Book(String title, String publishedYear, List<String> languages, String publisher, String genre,
			String description, float rating, int noOfPages, int noOfCopies, Author author) {
		super();
		this.title = title;
		this.publishedYear = publishedYear;
		this.languages = languages;
		this.publisher = publisher;
		this.genre = genre;
		this.description = description;
		this.rating = rating;
		this.noOfPages = noOfPages;
		this.author = author;
		this.noOfCopies = noOfCopies;
	}

}
