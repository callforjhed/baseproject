package com.gdeguzman.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gdeguzman.springboot.entity.Product;
import com.gdeguzman.springboot.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository repository;

	public List<Product> findAll() {
		return repository.findAll();
	}

	public Product findById(int id) {
		return repository.findById(id).get();
	}

	public void save(Product entity) {
		repository.save(entity);
	}

	public void delete(Product entity) {
		repository.delete(entity);
	}
	
	public boolean existsById(int id) {
		return repository.existsById(id);
	}

}
