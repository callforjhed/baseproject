package com.gdeguzman.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gdeguzman.springboot.entity.Product;
import com.gdeguzman.springboot.repository.ProductRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/lookup")
public class LookupTableController {

	@Autowired
	ProductRepository prodRepo;

	@RequestMapping("/products")
	public List<Product> listAll() {
		return prodRepo.findAll();
	}	

}
