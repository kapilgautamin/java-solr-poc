package com.solr.poc.models;

import java.util.List;

import com.poc.solr.dtos.AutocompleteResultsDto;

import lombok.Data;

@Data
public class AutocompleteResultsModel {
	private String Header;
	private List<AutocompleteResultsDto> Results;
}