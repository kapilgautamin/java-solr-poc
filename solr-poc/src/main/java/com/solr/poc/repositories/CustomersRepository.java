package com.solr.poc.repositories;

import org.springframework.data.solr.repository.SolrCrudRepository;

import com.solr.poc.entities.Customers;

public interface CustomersRepository extends SolrCrudRepository<Customers, String> {

}