package com.jumia.exercise.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jumia.exercise.contants.CountryConstans;
import com.jumia.exercise.contants.RegexConstans;
import com.jumia.exercise.model.Customer;

public class RegexUtils {
	
	public static boolean validaRegex(Customer customer, Pattern camaroonPattern) {
		Matcher mtch = camaroonPattern.matcher(customer.getPhone());
        return mtch.matches();
	}
	
	public static String getRegexByRequest(String country) {
		switch (country) {
		case CountryConstans.CAMEROON:
			return RegexConstans.CAMAROON_REGEX;
		case CountryConstans.ETHIOPIA:
			return RegexConstans.ETHIOPIA_REGEX;
		case CountryConstans.MOROCCO:
			return RegexConstans.MOROCCO_REGEX;
		case CountryConstans.MOZAMBIQUE:
			return RegexConstans.MOZAMBIQUE_REGEX;
		case CountryConstans.UGANDA:
			return RegexConstans.UGANDA_REGEX;
		default:
			break;
		}
		return null;
	}

}
