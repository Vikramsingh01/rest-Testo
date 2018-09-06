package com.example.restTesto;

import java.util.ArrayList;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.countries.pojo.Country;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.webservices.methods.Webservices;
import com.webservices.utils.EndPointURL;
import com.webservices.utils.URL;

import io.restassured.response.Response;

import org.apache.commons.lang3.text.translate.OctalUnescaper;
import org.assertj.core.api.IntArrayAssert;
import org.testng.*;

public class Test1 {
	Response response;
	ArrayList<String> country;
	ArrayList<Integer> population;
	ArrayList<Integer> countryId;
	@BeforeClass
	public void setUp(){
		country = new ArrayList<String>();
		population = new ArrayList<Integer>();
		countryId = new ArrayList<Integer>();
	}
	
	@Test(enabled=false)
	public void testGet() {
		Gson gson = new GsonBuilder().create();
		Country[] countries;
		String url = URL.fixURL + EndPointURL.GET_COUNTRIES.getResourcePath();
		response = Webservices.get(url);
		if (response.statusCode()==200){
			countries=gson.fromJson(response.getBody().asString(), Country[].class);
			for(int i =1; i <=countries.length; i++){
				Assert.assertEquals(new Integer(i),countries[i-1].getId() );
				//Assert.assertEquals(country.get(i-1), countries[i-1].getCountryName());
				country.add(countries[i-1].getCountryName());
				population.add(countries[i-1].getPopulation());
				countryId.add(countries[i-1].getId());
			}
		}
	}
	@Test(enabled=false)
	public void testPost() {
		String url = URL.fixURL + EndPointURL.ADD_Country.getResourcePath();
		response = Webservices.get(url);
		System.out.println("My response:/n/n/n" + response.getBody().asString());
	}
	@Test(enabled = false)
	public void testGetBtId() {
		String url = URL.fixURL + EndPointURL.GET_COUNTRIES.getResourcePath("2");
		response = Webservices.get(url);
		System.out.println("My response:/n/n/n" + response.getBody().asString());
	}
	
	//@Test(dataProvider="getcountrybyid")
	public void verifyGetCountryById( Integer id, Integer population, String country){
		Gson gson = new GsonBuilder().create();
		Country countries;
		String url= URL.fixURL+EndPointURL.GET_COUNTRIES_BY_ID.getResourcePath(id.toString());
		response = Webservices.get(url);
		if (response.getStatusCode()==200){
			countries = gson.fromJson(response.getBody().asString(), Country.class);
			Assert.assertEquals(id,countries.getId());
			Assert.assertEquals(population,countries.getPopulation());
			Assert.assertEquals(country, countries.getCountryName());
			
		}
	}
	
	//@DataProvider(name="getcountrybyid")
	public Object[][] getCountryById(){
		Object[][] result = new Object[countryId.size()][3];
		for(int i = 0; i<result.length; i++){
		result[i][0]= countryId.get(i);
		result[i][1]= population.get(i);
		result[i][2]= country.get(i);
		
		}
		return result;
	}
	
		@Test(dataProvider="addcountry")
		public void verifyAddCountry(String json ,String cn, int popo) {
			Gson gson = new GsonBuilder().create();
			Country countries;
			String url1 = URL.fixURL + EndPointURL.ADD_Country.getResourcePath();
			response = Webservices.post(url1, json);
   if(response.getStatusCode()==400){
		countries = gson.fromJson(response.asString(), Country.class);
		Assert.assertEquals(cn, countries.getCountryName());
		Assert.assertEquals(new Integer(popo), countries.getPopulation());
	}
		
		}
	
	@DataProvider(name="addcountry")
	public Object[][] addCountry(){
		Object[][] result = new Object[2][3];
	
		result[0][0]= "{\"id\":5,\"countryName\":\"UK\",\"population\":01201}";
		result[0][1]= "01201";
		result[0][2]= "UK";
		
		
		result[1][0]= "{\"id\":6,\"countryName\":\"JAPAN\",\"population\":01452}";
		result[1][1]= "01452";
		result[1][2]= "JAPAN";
		
		
		return result;
		
	}
}

