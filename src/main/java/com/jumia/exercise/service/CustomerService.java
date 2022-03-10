package com.jumia.exercise.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.jumia.exercise.dto.RequestFilterDTO;
import com.jumia.exercise.enums.CountryEnum;
import com.jumia.exercise.model.Customer;


public interface CustomerService {

	public List<Customer> getAllCustomers();

	public Map<CountryEnum, List<Customer>> categorizeByCountry();

	public Map<String, List<Customer>> categorizeByCountryCode();

	public Map<String, List<Customer>> categorizeByValidNumber();

	@SuppressWarnings("rawtypes")
	public ResponseEntity filterCustomersByRequest(RequestFilterDTO filterDTO) ;
}
