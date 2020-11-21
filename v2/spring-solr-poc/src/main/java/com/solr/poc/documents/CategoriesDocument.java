package com.solr.poc.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SolrDocument(collection = "categories")
public class CategoriesDocument {
	@Id
	@Indexed(value = "Id", name = "Id")
	private int id;
	
	@Indexed(required = true, value = "Category", name = "Category")
	private String category;
	
	@Indexed(required = true, value = "Url", name = "Url")
	private String url;
	
	@Indexed(value = "Description", name = "Description")
	private String description;
}