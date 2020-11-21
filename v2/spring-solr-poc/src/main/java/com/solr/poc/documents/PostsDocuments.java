package com.solr.poc.documents;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SolrDocument(collection = "posts")
public class PostsDocuments {
	@Id
	@Indexed(readonly = true, required = false)
	private int id;
	
	@Indexed(required = true, value = "title", name = "title")
	private String title;
	
	@Indexed(required = true, value = "url", name = "url")
	private String url;
	
	@Indexed(value = "shortdescription", name = "shortdescription")
	private String shortDescription;
	
	@Indexed(value = "completedescription", name = "completedescription")
	private String completeDescription;
	
	@Indexed(value = "blog", name = "blog")
	private BlogsDocument blog;
	
	@Indexed(value = "categories", name = "categories")
	private List<CategoriesDocument> categories;
}