package com.solr.poc.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solr.poc.entities.Customers;
import com.solr.poc.repositories.CustomersRepository;

@Service
public class CustomersService {
	@Autowired
	private CustomersRepository _customerRepository;
	private List<Customers> customers = new ArrayList<>();
	
	public List<Customers> getAllCustomers()
	{
		_customerRepository.findAll().forEach(customers::add);
		return customers;
	}
	
	public Optional<Customers> getCustomer(String customerID)
	{
		return _customerRepository.findById(customerID);
	}
	
	public void addCustomer(Customers customer)
	{
		_customerRepository.save(customer);
	}
	
	public void addCustomers(List<Customers> customers)
	{
		_customerRepository.saveAll(customers);
	}
}
