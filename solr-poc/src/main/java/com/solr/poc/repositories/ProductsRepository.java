package com.solr.poc.repositories;

import org.springframework.data.solr.repository.SolrCrudRepository;

import com.solr.poc.entities.Products;

public interface ProductsRepository extends SolrCrudRepository<Products, Integer> {
	public Products findByProductNumber(String productNumber);
}
