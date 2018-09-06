package com.countries.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Country {

	@SerializedName("id")
	@Expose
	private Integer id;
	@SerializedName("countryName")
	@Expose
	private String countryName;
	@SerializedName("population")
	@Expose
	private Integer population;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public Integer getPopulation() {
		return population;
	}

	public void setPopulation(Integer population) {
		this.population = population;
	}

}
