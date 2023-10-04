package com.api.book.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "books")
public class Book {
	
	@Id
	private String bookId;
	private String bookname;
	private String author;
	
	
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Book(String bookId, String bookname, String author) {
		super();
		this.bookId = bookId;
		this.bookname = bookname;
		this.author = author;
	}
	
	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", bookname=" + bookname + ", author=" + author + "]";
	}
	
	
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	public String getBookname() {
		return bookname;
	}
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
}
