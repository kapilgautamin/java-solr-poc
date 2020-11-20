package com.solr.poc.entities;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.SolrDocument;

import lombok.Data;

@Data
@SolrDocument(collection = "products")
public class Products {
	@Id
	@Field
	private int productID;

	@Field
	private String name;
	
	@Field
	private String productNumber;
	
	public Products(int productID, String name, String productNumber) {
		super();
		this.productID = productID;
		this.name = name;
		this.productNumber = productNumber;
	}
	
	public Products() {
	}
}