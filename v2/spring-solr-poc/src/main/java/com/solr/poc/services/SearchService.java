package com.solr.poc.services;

// import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.Field;
import org.springframework.data.solr.core.query.HighlightOptions;
import org.springframework.data.solr.core.query.SimpleHighlightQuery;
import org.springframework.data.solr.core.query.result.HighlightEntry;
import org.springframework.data.solr.core.query.result.HighlightEntry.Highlight;
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
		// var highlightOptions = new HighlightOptions();
		// highlightOptions.setSimplePrefix("<strong>");
		// highlightOptions.setSimplePostfix("<strong>");
		
		// SimpleHighlightQuery query = new SimpleHighlightQuery(new Criteria("Category"));
		// query.setHighlightOptions(highlightOptions);
		
		// HighlightPage<CategoriesDocument> hcategories = _solrTemplate.queryForHighlightPage("categories", query, CategoriesDocument.class);
		
		// List<CategoriesDocument> listOfCategories = new ArrayList<>();
		// categories.forEach(listOfCategories::add);
		
		HighlightPage<CategoriesDocument> categories = _categoriesRepository.findByCategory(term, page);
		
		var highlightedContent = categories.getHighlighted();
		
		for (HighlightEntry<CategoriesDocument> highlightEntry : highlightedContent) {
			highlightEntry.getHighlights();
		}
		
		var result = categories.getContent();
		for (CategoriesDocument categoriesDocument : result) {
			var hgs = categories.getHighlights(categoriesDocument);
			System.out.println(hgs.toString());
			
			for (Highlight highlight : hgs)
			{
				
				
				System.out.println(highlight.getSnipplets());
				System.out.println(highlight.getClass().getName());
				System.out.println(highlight.getField());
			}
		}
		
		 
		System.out.println("=========================================================================");
		System.out.println("Hey");
		System.out.println("=========================================================================");
		
		 highlightedContent.forEach(x -> System.out.println(x));
		
		return categories.getContent();
	}
}
