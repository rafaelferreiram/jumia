package com.jumia.exercise.dto;

import org.apache.logging.log4j.util.Strings;

public class RequestFilterDTO {
	
	private String country;
	
	private String state;
	
	private String code;

	public String getCountry() {
		if(Strings.isNotEmpty(country) && Strings.isNotBlank(country)) {
			return country.toUpperCase();
		}
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		if(Strings.isNotEmpty(state) && Strings.isNotBlank(state)) {
			return state.toUpperCase();
		}
		return state;
	}
	
	public String getCode() {
		return code;
	}

	public void setState(String state) {
		this.state = state;
	}
	
}
