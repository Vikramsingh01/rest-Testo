package com.example.restTesto;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.path.json.JsonPath.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.*;

public class Test4_PostReq {

	//@Test
	public void testWithRoot() {
		given().
		get("http://services.groupkt.com/country/get/iso3code/CYM").
		then().
		root("RestResponse.result").
				body("name", is("Cayman Islands")).
				body("alpha2_code", is("KY")).
				body("alpha3_code", is("CYM"));
	}

	// @Test
	public void testPostReq() {
		given().headers("AppKey", "Key-value").param("wfsfirst_name", "first").param("wfslast_name_", "last")
				.param("wfsemail", "test@test.com").when().post("http://api.fonts.com/rest/json/Accounts/").then()
				.statusCode(401).log().all();
	}

	// @Test
	public void testGetRestAsString() {
		String rspAsString = get("http://services.groupkt.com/country/search?text=lands").asString();
		System.out.println("My response:/n/n/n" + rspAsString);
	}

	// @Test
	public void testGetRestAsInputStream() throws IOException {
		InputStream rspAsStream = get("http://services.groupkt.com/country/search?text=lands").asInputStream();
		System.out.println("My response:/n/n/n" + rspAsStream);
	}

	// @Test
	public void testExtractDetailsUsingResponse() {
		Response response = when().get("http:jsonplaceholder.typicode.com/photos/1").then().extract().response();

		System.out.println("Content Type: " + response.contentType());
		System.out.println("Href:         " + response.path("url"));
		System.out.println("Status Code:  " + response.statusCode());
	}

	// @Test
	public void testExtractPathInOneLine() {
		String href = when().get("http:jsonplaceholder.typicode.com/photos/1").path("thumbnailUrl");
		System.out.println("Fetched URL 1:" + href);
		when().get(href).then().statusCode(200);

		String href2 = get("http:jsonplaceholder.typicode.com/photos/1").andReturn().jsonPath()
				.getString("thumbnailUrl");
		System.out.println("Fetched URL 2:" + href2);
		when().get(href2).then().statusCode(200);
	}
	//@Test
	public void testSchema(){
		given().    
		get("http://services.groupkt.com/country/get/iso3code/CYM").
		then().
		assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemaTest.json"));
	}
	//@Test
	public void tesGroovy(){
		String response = 
				when().
				get("http://services.groupkt.com/country/get/all").
				then().
				extract().
				asString();
		JsonPath jp = new JsonPath(response).setRoot("RestResponse.result");
		List<String> ls = jp.get("name");
		
		System.out.println("  "+ls.size());
	
	}
	//@Test
	public void testConnectReq(){
		when().
		request("CONNECT", "https://api.fonts.com/rest/json/Accounts/").
		then().
		statusCode(400);
	}
	//@Test
	public void testQueryParam(){
		given().
		queryParam("A", "A val").
		queryParam("B","B val").
		request("CONNECT", "https://api.fonts.com/rest/json/Accounts/").
		then().
		statusCode(400);
	}
	//@Test
	public void testFormParam(){
		given().
		formParam("A", "A val").
		formParam("B","B val").
		post("https://api.fonts.com/rest/json/Accounts/").
		then().
		statusCode(400);
	}
	//@Test
	public void testSetParameters(){
		given().
        pathParam("type", "json").
  
        when(). 
        post("https://api.fonts.com/rest/{type}/{section}/","Domains").
        then().
        statusCode(400);
	}
	//@Test
	  public void	testSetHeader(){
		given().
		header("k","v").
		header("k10","val1","val2","val3").
		headers("k1","v1","k2","v2").
		when().
		get("https://api.fonts.com/rest/json/Accounts/").
		then().
		statusCode(400);
		}
	  
		//@Test
		public void testResponse(){
			given().get("http://jsonplaceholder.typicode.com/photos/").then().assertThat().header("X-Powered-By", "Express");
			given().get("http://jsonplaceholder.typicode.com/photos/").then().assertThat().headers("Vary", "Origin, Accept-Encoding","Content-Type",containsString("json"));
		}
		
		//@Test
		public void testContentType(){
			given().get("http://jsonplaceholder.typicode.com/photos/").then().assertThat().contentType(ContentType.JSON);
		}
		
		//@Test
		public void testBodyResponse(){
			String responseString = get("http://www.thomas-bayer.com/sqlrest/CUSTOMER/02/").asString();
			given().get("http://www.thomas-bayer.com/sqlrest/CUSTOMER/02/").then().assertThat().body(equalTo(responseString));
		}
		//@Test
		public void testCookiesInResponse(){
			given().get("http://jsonplaceholder.typicode.com/comments").then().log().all().assertThat().cookie("__cfduid", "d37a115e3b0a459bdaa503c391899c6aa1536045778");
		}
		//@Test
		public void testBodyParamertersInResponse(){
			given().get("http://jsonplaceholder.typicode.com/photos/1").then().log().all().body("thumbnailUrl",response -> equalTo("https://via.placeholder.com/150/92c952"));
		}
		
}


















