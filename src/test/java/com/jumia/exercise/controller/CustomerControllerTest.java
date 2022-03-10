package com.jumia.exercise.controller;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.jumia.exercise.dto.RequestFilterDTO;
import com.jumia.exercise.enums.CountryEnum;
import com.jumia.exercise.model.Customer;
import com.jumia.exercise.service.impl.CustomerServiceImpl;



@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTest {
	
	@Mock
	private CustomerServiceImpl service;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
    private WebApplicationContext wac;  
	
	@Autowired
	@InjectMocks
	private CustomerController controller;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void shouldGetCustomers() throws Exception {
		RequestFilterDTO request = new RequestFilterDTO();
		
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
		
		when(service.getAllCustomers()).thenReturn(listOfCustomersExpected);
		
		String URI = "/customer/";
		
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON);
		
		mockMvc.perform(requestBuilder)
		.andExpect(MockMvcResultMatchers.status().is(200));
	}
	
	@Test
	public void shouldGetCustomersCategorizedByCountry() throws Exception {
		RequestFilterDTO request = new RequestFilterDTO();
		
		List<Customer> listOfCamerron = new ArrayList<>();
		Customer firstCameroonCustomer = new Customer(31, "EMILE CHRISTIAN KOUKOU DIKANDA HONORE", "(237) 697151594");
		listOfCamerron.add(firstCameroonCustomer);
		HashMap<CountryEnum, List<Customer>> categorizationByCountryExpeceted = new LinkedHashMap<>();		        
		categorizationByCountryExpeceted.put(CountryEnum.CAMEROON, listOfCamerron);
		
		when(service.categorizeByCountry()).thenReturn(categorizationByCountryExpeceted);
		
		String URI = "/customer/categorize/country";
		
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON);
		
		mockMvc.perform(requestBuilder)
		.andExpect(MockMvcResultMatchers.status().is(200));
	}
}
