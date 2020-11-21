package com.solr.poc.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

import lombok.Data;

@Data
@SolrDocument(collection = "products")
public class Products {
	@Id
	@Indexed(name = "id", type = "string")
	private String productID;

	@Indexed(name = "name", type = "string")
	private String name;
	
	@Indexed(name = "productNumber", type = "string")
	private String productNumber;
	
	public Products(String productID, String name, String productNumber) {
		super();
		this.productID = productID;
		this.name = name;
		this.productNumber = productNumber;
	}
	
	public Products() {
	}
}