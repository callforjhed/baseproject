package com.gdeguzman.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gdeguzman.springboot.entity.Basket;
import com.gdeguzman.springboot.repository.BasketRepository;

@Service
public class BasketService {

	@Autowired
	BasketRepository repository;

	public List<Basket> findAll() {
		return repository.findAll();
	}

	public Basket findById(int id) {
		return repository.findById(id).get();
	}

	public void save(Basket entity) {
		repository.save(entity);
	}

	public void delete(Basket entity) {
		repository.delete(entity);
	}
	
	public boolean existsById(int id) {
		return repository.existsById(id);
	}
	
	public Basket findByBasketname(String name) {
		return repository.findByBasketname(name);
	}

}
