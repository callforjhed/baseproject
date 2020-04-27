package com.gdeguzman.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gdeguzman.springboot.entity.BasketItem;
import com.gdeguzman.springboot.repository.BasketItemRepository;

@Service
public class BasketItemService {

	@Autowired
	BasketItemRepository repository;

	public List<BasketItem> findAll() {
		return repository.findAll();
	}

	public BasketItem findById(int id) {
		return repository.findById(id).get();
	}

	public void save(BasketItem entity) {
		repository.save(entity);
	}

	public void delete(BasketItem entity) {
		repository.delete(entity);
	}
	
	public boolean existsById(int id) {
		return repository.existsById(id);
	}

}
