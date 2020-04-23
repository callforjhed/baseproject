package com.gdeguzman.springboot.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gdeguzman.springboot.entity.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {

	public List<Product> findAll();
}
