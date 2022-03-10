package com.jumia.exercise.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.regex.Pattern;

import org.junit.Test;

import com.jumia.exercise.contants.CountryConstans;
import com.jumia.exercise.contants.RegexConstans;
import com.jumia.exercise.model.Customer;

public class RegexUtilsTest {

	@Test
	public void shouldGetRightRegex() {
		String regexByRequest = RegexUtils.getRegexByRequest(CountryConstans.CAMEROON);
		assertEquals(regexByRequest, RegexConstans.CAMAROON_REGEX);
	}

	@Test
	public void shouldValidateRegex() {
		Customer firstCameroonCustomer = new Customer(31, "EMILE CHRISTIAN KOUKOU DIKANDA HONORE", "(237) 697151594");
		boolean validaRegex = RegexUtils.validaRegex(firstCameroonCustomer, Pattern.compile(RegexConstans.CAMAROON_REGEX));
		assertTrue(validaRegex);
	}
}
