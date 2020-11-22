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
@SolrDocument(collection = "posts")
public class PostDocuments {
	@Id
	@Indexed(value = "Id", name = "Id")
	private int id;
	
	@Indexed(required = true, value = "Title", name = "Title")
	private String title;
	
	@Indexed(required = true, value = "TitleUrl", name = "TitleUrl")
	private String titleUrl;
	
	@Indexed(value = "ShortIntroduction", name = "ShortIntroduction")
	private String shortIntroduction;
	
	@Indexed(value = "CompleteDescription", name = "CompleteDescription")
	private String completeDescription;
	
	@Indexed(value = "BlogId", name = "BlogId", stored = false)
	private int blogId;
}