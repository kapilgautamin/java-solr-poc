package com.solr.poc.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.solr.poc.models.AutocompleteResultsModel;
import com.solr.poc.services.SearchService;

@RestController
@RequestMapping("/api/search")
public class SearchController {
	private static final int DEFAULT_PAGE_SIZE = 10;
	
	@Autowired
	private SearchService _searchService;
	
	@PostMapping("/auto-complete")
	public ResponseEntity<List<AutocompleteResultsModel>> getAutoCompleteResults(@RequestParam String content)
	{
		var results = _searchService.getAutoCompleteResults(content, PageRequest.of(0, DEFAULT_PAGE_SIZE));
		return new ResponseEntity<List<AutocompleteResultsModel>>(results, HttpStatus.OK);
	}
}