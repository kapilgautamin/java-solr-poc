package com.solr.poc.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;

import com.solr.poc.entities.Customers;

public interface CustomersRepository extends SolrCrudRepository<Customers, String> {

   @Query("firstName:*?0* OR lastName:*?0*")
   public Page<Customers> findByNamedQuery(String searchTerm, Pageable pageable);
       
}