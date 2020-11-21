package com.solr.poc.controllers;

import com.solr.poc.models.SearchInput;
import com.solr.poc.services.CustomersService;
import com.solr.poc.entities.Customers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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
