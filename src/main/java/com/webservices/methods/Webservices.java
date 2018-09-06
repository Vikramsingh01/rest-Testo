package com.webservices.methods;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Webservices {

	public static Response get(String url) {
		RequestSpecification requestSpecification = RestAssured.given();
		requestSpecification.contentType(ContentType.JSON);
		Response response = requestSpecification.get(url);
		return response;

	}

	
	 // public Response get(String url, Object id) { RequestSpecification
	 // requestSpecification = RestAssured.given(); Response response =
	 // requestSpecification.get(url, id); return response; }
	 

	public static Response post(String url, final Object StringJsonbody) {
		RequestSpecification requestSpecification = RestAssured.given().body(StringJsonbody);
		requestSpecification.contentType(ContentType.JSON);
		Response response = requestSpecification.post(url);
		return response;
	}

	public static Response patch(String url, Object id, Object StringJsonbody) {
		RequestSpecification requestSpecification = RestAssured.given().body(StringJsonbody);
		requestSpecification.contentType(ContentType.JSON);
		Response response = requestSpecification.patch(url, id);
		return response;
	}

	public static Response delete(String url, Object id) {
		RequestSpecification requestSpecification = RestAssured.given();
		requestSpecification.contentType(ContentType.JSON);
		Response response = requestSpecification.delete(url, id);
		return response;
	}

}