package com.solr.poc.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solr.poc.entities.Products;
import com.solr.poc.repositories.ProductsRepository;

@Service
public class ProductsService {
	@Autowired
	private ProductsRepository _productRepository;
	private List<Products> products = new ArrayList<>();
	
	public List<Products> getAllProducts()
	{
		_productRepository.findAll().forEach(products::add);
		return products;
	}
	
	public Products getProduct(String productNumber)
	{
		return _productRepository.findByProductNumber(productNumber);
	}
	
	public void addProduct(Products product)
	{
		_productRepository.save(product);
	}
	
	public void addProducts(List<Products> products)
	{
		_productRepository.saveAll(products);
	}
}
