package com.solr.poc.entities.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.solr.poc.entities.Customers;
import com.solr.poc.services.CustomersService;

@RestController
public class CustomersController {
	@Autowired
	private CustomersService _customersService;
	
	// @PostConstruct
	// public void addDefaultCustomers()
	// {
	// 	List<Customers> customers = new ArrayList<>();
	// 	customers.add(new Customers(1, "Orlando", "Gee"));
	// 	customers.add(new Customers(2, "Keith", "Harris"));
	// 	customers.add(new Customers(3, "Donna", "Carreras"));
	// 	customers.add(new Customers(4, "Janet", "Gates"));
	// 	customers.add(new Customers(5, "Lucy", "Harrington"));
	// 	customers.add(new Customers(6, "Rosmarie", "Carroll"));
	// 	customers.add(new Customers(7, "Dominic", "Gash"));
	// 	customers.add(new Customers(8, "Kathleen", "Garza"));
	// 	customers.add(new Customers(9, "Katherine", "Harding"));
	// 	customers.add(new Customers(10, "Johnny", "Caprio"));
	// 	customers.add(new Customers(11, "Christopher", "Beck"));
	// 	customers.add(new Customers(12, "David", "Liu"));
	// 	customers.add(new Customers(13, "John", "Beaver"));
	// 	customers.add(new Customers(14, "Jean", "Handley"));
	// 	customers.add(new Customers(15, "Jinghao", "Liu"));
	// 	_customersService.addCustomers(customers);
	// }
	
	@GetMapping("/customers")
	public List<Customers> getAllCustomers()
	{
		var customers = _customersService.getAllCustomers();
		return customers;
	}
	
	@GetMapping("/customers/{customerID}")
	public Optional<Customers> getCustomer(@PathVariable int customerID)
	{
		var customer = _customersService.getCustomer(customerID);
		return customer;
	}
}