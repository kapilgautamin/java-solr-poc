package com.solr.poc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HomeController {

	@RequestMapping(value = "/index")
   public String index() {
      return "index";
   }

   @RequestMapping(value = "/results")
   public String results() {
      return "results";
   }
}