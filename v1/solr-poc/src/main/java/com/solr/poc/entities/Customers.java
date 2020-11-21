package com.solr.poc.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

import lombok.Data;

@Data
@SolrDocument(collection = "customers")
public class Customers {
	@Id
	@Indexed(name = "id", type = "string")
	private String customerID;
	
	@Indexed(name = "firstName", type = "string")
	private String firstName;
	
	@Indexed(name = "lastName", type = "string")
	private String lastName;
	
	public Customers(String customerID, String firstName, String lastName) {
		super();
		this.customerID = customerID;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public Customers() {
	}
}