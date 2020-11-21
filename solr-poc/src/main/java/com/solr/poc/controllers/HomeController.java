package com.solr.poc.controllers;

import com.solr.poc.services.CustomersService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
   @Autowired
   private CustomersService _customersService;
   
   private final int DEFAULT_PAGE_SIZE = 10;
   
	@GetMapping("/index") 
   public String index() {
      return "index";
   }

   @PostMapping(value = "/results")   
   public String indexSubmit(@RequestParam String content, Model model) {
      // System.out.println("Posted is "+ content);
      model.addAttribute("output", _customersService.findByNamedQuery(content, PageRequest.of(0, DEFAULT_PAGE_SIZE)));
      return "results";
   }

}