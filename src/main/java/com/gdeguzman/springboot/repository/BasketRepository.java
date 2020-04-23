package com.gdeguzman.springboot.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gdeguzman.springboot.entity.Basket;

@Repository
public interface BasketRepository extends CrudRepository<Basket, Integer> {

	public List<Basket> findAll();
	public Basket findByBasketname(String name);
}
