package com.jumia.exercise.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jumia.exercise.contants.RegexConstans;
import com.jumia.exercise.dto.RequestFilterDTO;
import com.jumia.exercise.enums.CountryEnum;
import com.jumia.exercise.model.Customer;
import com.jumia.exercise.repository.CustomerRepository;
import com.jumia.exercise.service.CustomerService;
import com.jumia.exercise.utils.RegexUtils;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	private static final Logger log = LoggerFactory.getLogger(CustomerServiceImpl.class);

	@Autowired
	private CustomerRepository repository;
	
	@Override
	public List<Customer> getAllCustomers(){
		log.info("CustomerServiceImpl.getAllCustomers - start");
		List<Customer> customers = repository.findAll();
		log.info("CustomerServiceImpl.getAllCustomers - end ");
		return customers;
	}

	@Override
	public Map<CountryEnum, List<Customer>> categorizeByCountry(){
		log.info("CustomerServiceImpl.categorizeByCountry - start");
		List<Customer> customers = getAllCustomers();
		HashMap<CountryEnum, List<Customer>> categorizationByCountry = new LinkedHashMap<>();		        
		categorizationByCountry.put(CountryEnum.CAMEROON, customers.stream().filter(customer -> customer.getPhone().contains(CountryEnum.CAMEROON.getCountryCode())).collect(Collectors.toList()));
		categorizationByCountry.put(CountryEnum.ETHIOPIA, customers.stream().filter(customer -> customer.getPhone().contains(CountryEnum.ETHIOPIA.getCountryCode())).collect(Collectors.toList()));
		categorizationByCountry.put(CountryEnum.MOROCCO, customers.stream().filter(customer -> customer.getPhone().contains(CountryEnum.MOROCCO.getCountryCode())).collect(Collectors.toList()));
		categorizationByCountry.put(CountryEnum.MOZAMBIQUE, customers.stream().filter(customer -> customer.getPhone().contains(CountryEnum.MOZAMBIQUE.getCountryCode())).collect(Collectors.toList()));
		categorizationByCountry.put(CountryEnum.UGANDA, customers.stream().filter(customer -> customer.getPhone().contains(CountryEnum.UGANDA.getCountryCode())).collect(Collectors.toList()));
		log.info("CustomerServiceImpl.categorizeByCountry - end ");
		return categorizationByCountry;
	}

	@Override
	public Map<String, List<Customer>> categorizeByCountryCode(){
		log.info("CustomerServiceImpl.categorizeByCountryCode - start");
		List<Customer> customers = getAllCustomers();
		HashMap<String, List<Customer>> categorizationByCountryCode = new LinkedHashMap<>();		        
		categorizationByCountryCode.put(CountryEnum.CAMEROON.getCountryCode(), customers.stream().filter(customer -> customer.getPhone().contains(CountryEnum.CAMEROON.getCountryCode())).collect(Collectors.toList()));
		categorizationByCountryCode.put(CountryEnum.ETHIOPIA.getCountryCode(), customers.stream().filter(customer -> customer.getPhone().contains(CountryEnum.ETHIOPIA.getCountryCode())).collect(Collectors.toList()));
		categorizationByCountryCode.put(CountryEnum.MOROCCO.getCountryCode(), customers.stream().filter(customer -> customer.getPhone().contains(CountryEnum.MOROCCO.getCountryCode())).collect(Collectors.toList()));
		categorizationByCountryCode.put(CountryEnum.MOZAMBIQUE.getCountryCode(), customers.stream().filter(customer -> customer.getPhone().contains(CountryEnum.MOZAMBIQUE.getCountryCode())).collect(Collectors.toList()));
		categorizationByCountryCode.put(CountryEnum.UGANDA.getCountryCode(), customers.stream().filter(customer -> customer.getPhone().contains(CountryEnum.UGANDA.getCountryCode())).collect(Collectors.toList()));
		log.info("CustomerServiceImpl.categorizeByCountryCode - end ");
		return categorizationByCountryCode;
	}
	
	@Override
	public Map<String, List<Customer>> categorizeByValidNumber() {
		log.info("CustomerServiceImpl.categorizeByValidNumber - start");
		List<Customer> customers = getAllCustomers();
		List<Customer> validNumbers = new ArrayList<>();
		HashMap<String, List<Customer>> categorizationByValidNumber = new LinkedHashMap<>();
		
		validNumbers.addAll(filterValidNumberByCountry(customers, CountryEnum.CAMEROON, RegexConstans.CAMAROON_REGEX));
		validNumbers.addAll(filterValidNumberByCountry(customers, CountryEnum.ETHIOPIA, RegexConstans.ETHIOPIA_REGEX));
		validNumbers.addAll(filterValidNumberByCountry(customers, CountryEnum.MOROCCO, RegexConstans.MOROCCO_REGEX));
		validNumbers.addAll(filterValidNumberByCountry(customers, CountryEnum.MOZAMBIQUE, RegexConstans.MOZAMBIQUE_REGEX));
		validNumbers.addAll(filterValidNumberByCountry(customers, CountryEnum.UGANDA,RegexConstans.UGANDA_REGEX));
		
		categorizationByValidNumber.put("VALID", validNumbers);
		categorizationByValidNumber.put("NOT-VALID", customers.stream().filter(customer -> !validNumbers.contains(customer)).collect(Collectors.toList()));
		log.info("CustomerServiceImpl.categorizeByValidNumber - end ");
		return categorizationByValidNumber;
	}


	@SuppressWarnings("rawtypes")
	@Override
	public ResponseEntity filterCustomersByRequest(RequestFilterDTO filterDTO) {
		List<Customer> customers = getAllCustomers();
		if (isFilterByCountry(filterDTO)) {
			List<Customer> filteredBy = customers.stream()
					.filter(customer -> customer.getPhone()
							.contains(CountryEnum.valueOf(filterDTO.getCountry()).getCountryCode()))
					.collect(Collectors.toList());
			return ResponseEntity.status(HttpStatus.OK).body(filteredBy);
		}
		
		if (isFilterByState(filterDTO)) {
			Map<String, List<Customer>> validCutomers = categorizeByValidNumber();
			return ResponseEntity.status(HttpStatus.OK).body(validCutomers.get(filterDTO.getState()));
		}
		else {	
			Pattern pattern = Pattern.compile(RegexUtils.getRegexByRequest(filterDTO.getCountry()));
			List<Customer> filteredBy = customers.stream()
					.filter(customer -> customer.getPhone()
							.contains(CountryEnum.valueOf(filterDTO.getCountry().toUpperCase()).getCountryCode()))
					.collect(Collectors.toList());
			List<Customer> collect = "VALID".equalsIgnoreCase(filterDTO.getState()) ? filteredBy.stream().filter(customer -> RegexUtils.validaRegex(customer, pattern)).collect(Collectors.toList()) :  filteredBy.stream().filter(customer -> !RegexUtils.validaRegex(customer, pattern)).collect(Collectors.toList());
			return ResponseEntity.status(HttpStatus.OK).body(collect);
		}		
	}
	
	private List<Customer> filterValidNumberByCountry(List<Customer> customers, CountryEnum country, String countryRegex) {
		List<Customer> countryNumbers = customers.stream().filter(customer -> customer.getPhone().contains(country.getCountryCode())).collect(Collectors.toList());
		Pattern pattern = Pattern.compile(countryRegex);
		return countryNumbers.stream().filter(customer -> RegexUtils.validaRegex(customer, pattern)).collect(Collectors.toList());
	}

	private boolean isFilterByState(RequestFilterDTO filterDTO) {
		return Strings.isNotBlank(filterDTO.getState()) && Strings.isBlank(filterDTO.getCountry());
	}

	private boolean isFilterByCountry(RequestFilterDTO filterDTO) {
		return Strings.isNotBlank(filterDTO.getCountry()) && Strings.isBlank(filterDTO.getState());
	}

	
}
