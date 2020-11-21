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
@SolrDocument(collection = "blogs")
public class BlogsDocument {
	@Id
	@Indexed(value = "Id", name = "Id")
	private int id;
	
	@Indexed(required = true, value = "Name", name = "Name")
	private String name;
	
	@Indexed(required = true, value = "Link", name = "Link")
	private String url;
}