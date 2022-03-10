package com.jumia.exercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jumia.exercise.model.Customer;


@Repository 
public interface CustomerRepository extends JpaRepository<Customer, Integer>{

}
