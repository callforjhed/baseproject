package com.gdeguzman.springboot.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gdeguzman.springboot.entity.BasketItem;

@Repository
public interface BasketItemRepository extends CrudRepository<BasketItem, Integer> {

	public List<BasketItem> findAll();
	public BasketItem findByProductname(String productName);
}
