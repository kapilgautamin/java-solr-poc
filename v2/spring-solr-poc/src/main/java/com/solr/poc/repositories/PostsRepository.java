package com.solr.poc.repositories;

import org.springframework.data.solr.repository.SolrCrudRepository;

import com.solr.poc.documents.PostsDocuments;

public interface PostsRepository extends SolrCrudRepository<PostsDocuments, Integer> {
	
}
