package com.solr.poc.services;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.HighlightOptions;
import org.springframework.data.solr.core.query.HighlightQuery;
import org.springframework.data.solr.core.query.SimpleHighlightQuery;
import org.springframework.data.solr.core.query.result.HighlightPage;
import org.springframework.stereotype.Service;

import com.solr.poc.documents.CategoriesDocument;
import com.solr.poc.repositories.CategoriesRepository;

@Service
public class SearchService {
	
	@Autowired
	private CategoriesRepository _categoriesRepository;
	
	@Resource
	private SolrTemplate _solrTemplate;
	
	public List<CategoriesDocument> getAutoCompleteResults(String term, Pageable page)
	{
		HighlightQuery hq = new SimpleHighlightQuery(new Criteria("Category").contains(term));
		HighlightOptions hlOptions = new HighlightOptions().setSimplePostfix("<b>").setSimplePrefix("</b>");
		hq.setHighlightOptions(hlOptions);
		HighlightPage<CategoriesDocument> hgp = 
				_solrTemplate.queryForHighlightPage("categories", hq, CategoriesDocument.class);
		
		for (var hlDoc : hgp.getHighlighted()) {
			for (var hl : hlDoc.getHighlights()) {
				System.out.println(hl.getSnipplets());
			}
		}
		
		for (CategoriesDocument categoriesDocument : hgp) {
			System.out.println(categoriesDocument.toString());
			System.out.println(categoriesDocument.getCategory());
			System.out.println(categoriesDocument.getUrl());
		}
		
		
		
		// var highlightOptions = new HighlightOptions();
		// highlightOptions.setSimplePrefix("<strong>");
		// highlightOptions.setSimplePostfix("<strong>");
		
		// SimpleHighlightQuery query = new SimpleHighlightQuery(new Criteria("Category"));
		// query.setHighlightOptions(highlightOptions);
		
		// HighlightPage<CategoriesDocument> hcategories = _solrTemplate.queryForHighlightPage("categories", query, CategoriesDocument.class);
		
		// List<CategoriesDocument> listOfCategories = new ArrayList<>();
		// categories.forEach(listOfCategories::add);
		
		HighlightPage<CategoriesDocument> categories = _categoriesRepository.findByCategory(term, page);
		
		return categories.getContent();
	}
}
