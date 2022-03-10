package com.jumia.exercise.enums;

public enum CountryEnum {
	CAMEROON("CAMEROON","(237)"), ETHIOPIA("ETHIOPIA","(251)"), MOROCCO("MOROCCO","(212)"), MOZAMBIQUE("MOZAMBIQUE","(258)"), UGANDA("UGANDA","(256)");

	private String countryName;
	private String countryCode;

	CountryEnum(String countryName, String countryCode) {
		this.countryName = countryName;
		this.countryCode = countryCode;
	}
	
	public String getCountryName() {
		return countryName;
	}

	public String getCountryCode() {
		return countryCode;
	}
}
