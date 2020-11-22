package com.solr.poc.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
// import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.result.HighlightPage;
import org.springframework.stereotype.Service;

import com.poc.solr.dtos.AutocompleteResultsDto;
import com.solr.poc.documents.BlogsDocument;
import com.solr.poc.documents.PostDocuments;
import com.solr.poc.models.AutocompleteResultsModel;
import com.solr.poc.repositories.BlogsRepository;
import com.solr.poc.repositories.PostsRepository;

@Service
public class SearchService {
	
	@Autowired
	private BlogsRepository _blogsRepository;
	
	@Autowired
	private PostsRepository _postsRepository;
	
	// @Resource
	// private SolrTemplate _solrTemplate;
	
	public List<AutocompleteResultsModel> getAutoCompleteResults(String term, Pageable page)
	{
		List<AutocompleteResultsModel> results = new ArrayList<AutocompleteResultsModel>();
		
		Iterable<BlogsDocument> blogDocuments = _blogsRepository.findAll();
		HighlightPage<PostDocuments> postDocuments = _postsRepository.findByTitle(term, page);
		
		var postResultDocuments = postDocuments.getContent();
		
		for (BlogsDocument blogsDocument : blogDocuments)
		{
			var postsPerBlog = postResultDocuments.stream().filter(x -> x.getBlogId() == blogsDocument.getId()).collect(Collectors.toList());
			if (postsPerBlog != null && postsPerBlog.size() > 0) {
				var autoCompletionResults = new ArrayList<AutocompleteResultsDto>();
				var resultsModel = new AutocompleteResultsModel();
				resultsModel.setHeader(blogsDocument.getName());
				
				for (PostDocuments post : postsPerBlog)
				{
					autoCompletionResults.add(new AutocompleteResultsDto(post.getTitle(), post.getTitleUrl()));					
				}
				
				resultsModel.setResults(autoCompletionResults);
				results.add(resultsModel);
			}
		}
		return results;
	}
}