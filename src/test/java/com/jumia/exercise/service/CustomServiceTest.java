package com.jumia.exercise.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.jumia.exercise.dto.RequestFilterDTO;
import com.jumia.exercise.enums.CountryEnum;
import com.jumia.exercise.model.Customer;
import com.jumia.exercise.repository.CustomerRepository;
import com.jumia.exercise.service.impl.CustomerServiceImpl;

public class CustomServiceTest {

	@InjectMocks
	private CustomerService service = new CustomerServiceImpl();

	@Mock
	private CustomerRepository repository;

	@Before
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void shouldGetCustomersSuccessfully() {
		List<Customer> listOfCustomersExpected = new ArrayList<>();
		when(repository.findAll()).thenReturn(listOfCustomersExpected);
		List<Customer> response = service.getAllCustomers();
		assertEquals(listOfCustomersExpected, response);
	}

	@Test
	public void shouldGetCustomersCategorizedByCountry() {
		List<Customer> listOfCustomersExpected = new ArrayList<>();
		Customer firstCameroonCustomer = new Customer(31, "EMILE CHRISTIAN KOUKOU DIKANDA HONORE", "(237) 697151594");
		Customer firstEthiopiaCustomer = new Customer(22, "shop23 sales", "(251) 9773199405");
		Customer firstMorrocoCustomer = new Customer(0, "Walid Hammadi", "(212) 6007989253");
		Customer firstMozambiqueCustomer = new Customer(7, "Edunildo Gomes Alberto", "(258) 847651504");
		Customer firstUgandaCustomer = new Customer(15, "JACKSON NELLY", "(256) 775069443");
		listOfCustomersExpected.add(firstCameroonCustomer);
		listOfCustomersExpected.add(firstEthiopiaCustomer);
		listOfCustomersExpected.add(firstMorrocoCustomer);
		listOfCustomersExpected.add(firstMozambiqueCustomer);
		listOfCustomersExpected.add(firstUgandaCustomer);

		when(repository.findAll()).thenReturn(listOfCustomersExpected);

		Map<CountryEnum, List<Customer>> categorizeByCountry = service.categorizeByCountry();

		assertEquals(firstCameroonCustomer, categorizeByCountry.get(CountryEnum.CAMEROON).get(0));
		assertEquals(firstEthiopiaCustomer, categorizeByCountry.get(CountryEnum.ETHIOPIA).get(0));
		assertEquals(firstMorrocoCustomer, categorizeByCountry.get(CountryEnum.MOROCCO).get(0));
		assertEquals(firstMozambiqueCustomer, categorizeByCountry.get(CountryEnum.MOZAMBIQUE).get(0));
		assertEquals(firstUgandaCustomer, categorizeByCountry.get(CountryEnum.UGANDA).get(0));
	}

	@Test
	public void shouldGetCustomersCategorizedByCountryCode() {
		List<Customer> listOfCustomersExpected = new ArrayList<>();
		Customer firstCameroonCustomer = new Customer(31, "EMILE CHRISTIAN KOUKOU DIKANDA HONORE", "(237) 697151594");
		Customer firstEthiopiaCustomer = new Customer(22, "shop23 sales", "(251) 9773199405");
		Customer firstMorrocoCustomer = new Customer(0, "Walid Hammadi", "(212) 6007989253");
		Customer firstMozambiqueCustomer = new Customer(7, "Edunildo Gomes Alberto", "(258) 847651504");
		Customer firstUgandaCustomer = new Customer(15, "JACKSON NELLY", "(256) 775069443");
		listOfCustomersExpected.add(firstCameroonCustomer);
		listOfCustomersExpected.add(firstEthiopiaCustomer);
		listOfCustomersExpected.add(firstMorrocoCustomer);
		listOfCustomersExpected.add(firstMozambiqueCustomer);
		listOfCustomersExpected.add(firstUgandaCustomer);

		when(repository.findAll()).thenReturn(listOfCustomersExpected);

		Map<String, List<Customer>> categorizeByCountryCode = service.categorizeByCountryCode();

		assertEquals(firstCameroonCustomer, categorizeByCountryCode.get(CountryEnum.CAMEROON.getCountryCode()).get(0));
		assertEquals(firstEthiopiaCustomer, categorizeByCountryCode.get(CountryEnum.ETHIOPIA.getCountryCode()).get(0));
		assertEquals(firstMorrocoCustomer, categorizeByCountryCode.get(CountryEnum.MOROCCO.getCountryCode()).get(0));
		assertEquals(firstMozambiqueCustomer,
				categorizeByCountryCode.get(CountryEnum.MOZAMBIQUE.getCountryCode()).get(0));
		assertEquals(firstUgandaCustomer, categorizeByCountryCode.get(CountryEnum.UGANDA.getCountryCode()).get(0));
	}

	@Test
	public void shouldGetValidAndInvalidCustomers() {
		List<Customer> listOfCustomersExpected = new ArrayList<>();
		Customer firstCameroonCustomer = new Customer(31, "EMILE CHRISTIAN KOUKOU DIKANDA HONORE", "(237) 697151594");
		Customer firstEthiopiaCustomer = new Customer(22, "shop23 sales", "(251) 9773199405");
		Customer firstMorrocoCustomer = new Customer(0, "Walid Hammadi", "(212) 6007989253");
		Customer firstMozambiqueCustomer = new Customer(7, "Edunildo Gomes Alberto", "(258) 847651504");
		Customer firstUgandaCustomer = new Customer(15, "JACKSON NELLY", "(256) 775069443");
		listOfCustomersExpected.add(firstCameroonCustomer);
		listOfCustomersExpected.add(firstEthiopiaCustomer);
		listOfCustomersExpected.add(firstMorrocoCustomer);
		listOfCustomersExpected.add(firstMozambiqueCustomer);
		listOfCustomersExpected.add(firstUgandaCustomer);

		when(repository.findAll()).thenReturn(listOfCustomersExpected);

		Map<String, List<Customer>> categorizeByValidNumber = service.categorizeByValidNumber();

		assertEquals(3, categorizeByValidNumber.get("VALID").size());
		assertEquals(2, categorizeByValidNumber.get("NOT-VALID").size());

	}

	@Test
	@SuppressWarnings("unchecked")
	public void shouldGetCustomersFilteredByCountry() {
		List<Customer> listOfCustomersExpected = new ArrayList<>();
		Customer firstCameroonCustomer = new Customer(31, "EMILE CHRISTIAN KOUKOU DIKANDA HONORE", "(237) 697151594");
		Customer firstEthiopiaCustomer = new Customer(22, "shop23 sales", "(251) 9773199405");
		Customer firstMorrocoCustomer = new Customer(0, "Walid Hammadi", "(212) 6007989253");
		Customer firstMozambiqueCustomer = new Customer(7, "Edunildo Gomes Alberto", "(258) 847651504");
		Customer firstUgandaCustomer = new Customer(15, "JACKSON NELLY", "(256) 775069443");
		listOfCustomersExpected.add(firstCameroonCustomer);
		listOfCustomersExpected.add(firstEthiopiaCustomer);
		listOfCustomersExpected.add(firstMorrocoCustomer);
		listOfCustomersExpected.add(firstMozambiqueCustomer);
		listOfCustomersExpected.add(firstUgandaCustomer);

		RequestFilterDTO request = new RequestFilterDTO();
		request.setCountry("cameroon");

		when(repository.findAll()).thenReturn(listOfCustomersExpected);

		ResponseEntity<List<Customer>> filterCustomersByRequest = service.filterCustomersByRequest(request);

		
		assertEquals(firstCameroonCustomer, filterCustomersByRequest.getBody().get(0));
		assertEquals(200, filterCustomersByRequest.getStatusCodeValue());

	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void shouldGetCustomersFilteredByState() {
		List<Customer> listOfCustomersExpected = new ArrayList<>();
		Customer firstCameroonCustomer = new Customer(31, "EMILE CHRISTIAN KOUKOU DIKANDA HONORE", "(237) 697151594");
		Customer firstEthiopiaCustomer = new Customer(22, "shop23 sales", "(251) 9773199405");
		Customer firstMorrocoCustomer = new Customer(0, "Walid Hammadi", "(212) 6007989253");
		Customer firstMozambiqueCustomer = new Customer(7, "Edunildo Gomes Alberto", "(258) 847651504");
		Customer firstUgandaCustomer = new Customer(15, "JACKSON NELLY", "(256) 775069443");
		listOfCustomersExpected.add(firstCameroonCustomer);
		listOfCustomersExpected.add(firstEthiopiaCustomer);
		listOfCustomersExpected.add(firstMorrocoCustomer);
		listOfCustomersExpected.add(firstMozambiqueCustomer);
		listOfCustomersExpected.add(firstUgandaCustomer);

		RequestFilterDTO request = new RequestFilterDTO();
		request.setState("valid");

		when(repository.findAll()).thenReturn(listOfCustomersExpected);

		ResponseEntity<List<Customer>> filterCustomersByRequest = service.filterCustomersByRequest(request);

		assertEquals(3, filterCustomersByRequest.getBody().size());
		assertEquals(200, filterCustomersByRequest.getStatusCodeValue());

	}


}
