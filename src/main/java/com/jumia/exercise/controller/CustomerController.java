package com.jumia.exercise.controller;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jumia.exercise.dto.RequestFilterDTO;
import com.jumia.exercise.enums.CountryEnum;
import com.jumia.exercise.model.Customer;
import com.jumia.exercise.service.impl.CustomerServiceImpl;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerServiceImpl service;

	@SuppressWarnings("rawtypes")
	@GetMapping(value = "/")
	public ResponseEntity getAllCustomers(RequestFilterDTO filterDTO) {
		try {
			List<Customer> customers = service.getAllCustomers();

			if (!Objects.isNull(filterDTO)) {
				return service.filterCustomersByRequest(filterDTO);
			}

			if (!customers.isEmpty()) {
				return ResponseEntity.status(HttpStatus.OK).body(customers);
			}
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No Customers Found to List.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Sorry, we faced some errors during this request.");
		}
	}

	@SuppressWarnings("rawtypes")
	@GetMapping(value = "/categorize/country")
	public ResponseEntity categorizeCountry() {
		try {

			Map<CountryEnum, List<Customer>> categorizeByCountry = service.categorizeByCountry();
			if (!categorizeByCountry.isEmpty()) {
				return ResponseEntity.status(HttpStatus.OK).body(categorizeByCountry);
			}
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No Customers Found to List.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Sorry, we faced some errors during this request.");
		}
	}

	@SuppressWarnings("rawtypes")
	@GetMapping(value = "/categorize/country-code")
	public ResponseEntity categorizeCountryCode() {
		try {
			Map<String, List<Customer>> categorizeByCountry = service.categorizeByCountryCode();
			if (!categorizeByCountry.isEmpty()) {
				return ResponseEntity.status(HttpStatus.OK).body(categorizeByCountry);
			}
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No Customers Found to List.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Sorry, we faced some errors during this request.");
		}
	}

	@SuppressWarnings("rawtypes")
	@GetMapping(value = "/categorize/valid")
	public ResponseEntity categorizeValidNumber() {
		try {
			Map<String, List<Customer>> categorizeByCountry = service.categorizeByValidNumber();
			if (!categorizeByCountry.isEmpty()) {
				return ResponseEntity.status(HttpStatus.OK).body(categorizeByCountry);
			}
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No Customers Found to List.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Sorry, we faced some errors during this request.");
		}
	}
}
