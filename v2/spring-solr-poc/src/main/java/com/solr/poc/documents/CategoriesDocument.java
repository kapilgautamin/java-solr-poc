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
	@Indexed(readonly = true, required = false)
	private int id;
	
	@Indexed(required = true, value = "category", name = "category")
	private String category;
	
	@Indexed(required = true, value = "category_url", name = "category_url")
	private String url;
	
	@Indexed(value = "category_description", name = "category_description")
	private String description;
}