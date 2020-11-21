package com.solr.poc.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.solr.poc.documents.CategoriesDocument;
import com.solr.poc.services.SearchService;

@RestController
@RequestMapping("/api/search")
public class SearchController {
	@Autowired
	private SearchService _searchService;
	
	@RequestMapping("/auto-complete/{term}")
	public ResponseEntity<List<CategoriesDocument>> getAutoCompleteResults(@PathVariable String term, Pageable page)
	{
		var categories = _searchService.getAutoCompleteResults(term, page);
		return new ResponseEntity<List<CategoriesDocument>>(categories, HttpStatus.OK);
	}
}
