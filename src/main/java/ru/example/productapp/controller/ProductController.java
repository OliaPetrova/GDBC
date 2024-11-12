package ru.example.productapp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.example.productapp.repository.ProductRepository;

import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class ProductController {

	private final ProductRepository productRepository;

	public ProductController(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@GetMapping("/products/fetch-product")
	public List<String> getMethodName(@RequestParam String name) {
		return productRepository.getProductName(name);
	}

}
