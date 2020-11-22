package com.solr.poc.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.query.result.HighlightPage;
import org.springframework.data.solr.repository.Highlight;
import org.springframework.data.solr.repository.SolrCrudRepository;

import com.solr.poc.documents.CategoriesDocument;

public interface CategoriesRepository extends SolrCrudRepository<CategoriesDocument, Integer> {
	@Highlight(prefix = "<em>", postfix = "</em>")
	HighlightPage<CategoriesDocument> findByCategory(String category, Pageable pageable);
}
