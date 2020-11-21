package com.solr.poc.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.solr.poc.entities.Products;
import com.solr.poc.services.ProductsService;

@RestController
public class ProductsController {
	@Autowired
	private ProductsService _productsService;
	
	// @PostConstruct
	// public void addDefaultProducts()
	// {
	// 	List<Products> products = new ArrayList<>();
	// 	products.add(new Products(1, "AWC Logo Cap", "CA-1098"));
	// 	products.add(new Products(2, "HL Road Frame - Black, 58", "FR-R92B-58"));
	// 	products.add(new Products(3, "HL Road Frame - Red, 58", "FR-R92R-58"));
	// 	products.add(new Products(4, "Sport-100 Helmet, Red", "HL-U509-R"));
	// 	products.add(new Products(5, "Sport-100 Helmet, Black", "HL-U509"));
	// 	products.add(new Products(6, "Mountain Bike Socks, M", "SO-B909-M"));
	// 	products.add(new Products(7, "Mountain Bike Socks, L", "SO-B909-L"));
	// 	products.add(new Products(8, "Sport-100 Helmet, Blue", "HL-U509-B"));
	// 	products.add(new Products(9, "Long-Sleeve Logo Jersey, S", "LJ-0192-S"));
	// 	products.add(new Products(10, "Long-Sleeve Logo Jersey, M", "LJ-0192-M"));
	// 	_productsService.addProducts(products);
	// }
	
	@GetMapping("/products")
	public List<Products> getAllProducts()
	{
		var products = _productsService.getAllProducts();
		return products;
	}
	
	@GetMapping("/products/{productNumber}")
	public Products getProducts(@PathVariable String productNumber)
	{
		var product = _productsService.getProduct(productNumber);
		return product;
	}
}