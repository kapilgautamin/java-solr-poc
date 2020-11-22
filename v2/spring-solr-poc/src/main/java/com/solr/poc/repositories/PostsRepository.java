package com.solr.poc.repositories;

import org.springframework.data.solr.repository.SolrCrudRepository;

import com.solr.poc.documents.PostDocuments;

public interface PostsRepository extends SolrCrudRepository<PostDocuments, Integer> {
	
}
