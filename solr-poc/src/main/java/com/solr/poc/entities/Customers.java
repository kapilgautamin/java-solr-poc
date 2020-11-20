package com.solr.poc.entities;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

import lombok.Data;

@Data
@SolrDocument(collection = "customers")
public class Customers {
	@Id
	@Field
	private int customerID;
	
	@Field
	private String firstName;
	
	@Field
	private String lastName;
	
	public Customers(int customerID, String firstName, String lastName) {
		super();
		this.customerID = customerID;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public Customers() {
	}
}