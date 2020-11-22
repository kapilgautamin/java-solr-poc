package com.solr.poc.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.query.result.HighlightPage;
import org.springframework.data.solr.repository.Highlight;
import org.springframework.data.solr.repository.SolrCrudRepository;

import com.solr.poc.documents.PostDocuments;

public interface PostsRepository extends SolrCrudRepository<PostDocuments, Integer> {
	@Highlight(prefix = "<b>", postfix = "</b>")
	HighlightPage<PostDocuments> findByTitle(String title, Pageable page);
}
