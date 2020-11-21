package com.solr.poc.controllers;

import com.solr.poc.services.CustomersService;
import com.solr.poc.entities.Customers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController {

   @Autowired
   private CustomersService _customersService;

   @RequestMapping(value = "/search/{term}")
   public Page<Customers> search(@PathVariable String term) {
   var customers = _customersService.findByNamedQuery(term, PageRequest.of(0, 5));
   return customers;
   }

}
