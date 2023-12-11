package com.api.book;

public enum ApiEndPoints {
	BASEURL(RequestMappingConstants.BASEURL), BOOKS(RequestMappingConstants.BOOKS),
	AUTHORS(RequestMappingConstants.AUTHORS), ADDRESS(RequestMappingConstants.AUTHORS),
	REVIEWS(RequestMappingConstants.REVIEWS), UPDATEBOOKNAME("updateBookName");

	public final String path;

	ApiEndPoints(String path) {
		this.path = path;
	}

	public String getPath() {
		return this.path;
	}

}