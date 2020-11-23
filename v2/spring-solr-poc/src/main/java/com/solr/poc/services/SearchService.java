package com.solr.poc.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.params.CommonParams;
import org.apache.solr.common.params.MapSolrParams;
import org.apache.solr.common.params.ModifiableSolrParams;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.SolrTemplate;
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
	
	@Resource
	private SolrTemplate _solrTemplate;
	
	public List<AutocompleteResultsModel> getAutoCompleteResults(String term, Pageable page)
	{
		// testingnew(term);
		// testing(term);
		
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
	
	public void testing(String term)
	{
		final SolrClient client = _solrTemplate.getSolrClient();

		final Map<String, String> queryParamMap = new HashMap<String, String>();
		queryParamMap.put("q", term);
		queryParamMap.put("hl", "true");
		queryParamMap.put("hl.simple.pre", "<b>");
		queryParamMap.put("hl.simple.post", "<b>");
		queryParamMap.put("hl.fl", "*");
		queryParamMap.put("hl.fragsize", "0");
		MapSolrParams queryParams = new MapSolrParams(queryParamMap);

		try {
			final QueryResponse response = client.query("posts", queryParams);
			var test = response;
		} catch (SolrServerException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void testingnew(String term)
	{
		SolrQuery params = prepareQuery(term);
		var solrClient = _solrTemplate.getSolrClient();
		try {
			var pingResponse = solrClient.ping("posts");
			
			final QueryResponse response = solrClient.query("posts", params);
			var documents = response.getResults();
			var highligting = response.getHighlighting();
			
			for (SolrDocument solrDocument : documents) {
				var test = solrDocument;
			}
			
		} catch (SolrServerException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private SolrQuery prepareQuery(String queryTerm) {
	    SolrQuery queryParams = new SolrQuery();
	    queryParams.setQuery(queryTerm);
	    // queryParams.set(CommonParams.Q, queryTerm);
	    
	    queryParams
	    .setHighlight(true)
	    .setHighlightSnippets(1)
	    .setHighlightSimplePre("<b>")
	    .setHighlightSimplePost("</b>");
	    
	    return queryParams;
	}
	
}