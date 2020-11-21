package com.solr.poc.repositories;

import org.springframework.data.solr.repository.SolrCrudRepository;

import com.solr.poc.documents.CategoriesDocument;

public interface CategoriesRepository extends SolrCrudRepository<CategoriesDocument, Integer> {
	
}
