package com.solr.poc.controllers;

import com.solr.poc.models.SearchInput;
import com.solr.poc.services.CustomersService;

import org.apache.tomcat.util.http.parser.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.solr.core.RequestMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
   @Autowired
   private CustomersService _customersService;
   
	@GetMapping("/index") 
   public String index() {
      return "index";
   }

   private final int DEFAULT_PAGE_SIZE = 10;

   @PostMapping(value = "/results")   
   public String indexSubmit(@RequestParam String content, Model model) {
      // System.out.println("Posted is "+ content);
      model.addAttribute("output", _customersService.findByNamedQuery(content, PageRequest.of(0, 5)));
      return "results";
   }
}