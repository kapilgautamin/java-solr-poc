package com.solr.poc.repositories;

import org.springframework.data.solr.repository.SolrCrudRepository;

import com.solr.poc.documents.BlogsDocument;

public interface BlogsRepository extends SolrCrudRepository<BlogsDocument, Integer> {
	
}
