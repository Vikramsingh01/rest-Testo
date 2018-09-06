package com.webservices.utils;

public enum EndPointURL {

	GET_COUNTRIES("/countries/"), GET_COUNTRIES_BY_ID("/countries/"), ADD_Country("/countries/"), UPDATE_COUNTRY("/countries/"), DELETE_COUNTRY("/countries/");

	String resourcePath;

	private EndPointURL(String resourcePath) {
		this.resourcePath = resourcePath;
	}
	public String getResourcePath(){
		return this.resourcePath;
	}
	public String getResourcePath(String data) {
		return this.resourcePath+data;
	}

}
