package com.poc.solr.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AutocompleteResultsDto {
	private String title;
	private String url;
}
