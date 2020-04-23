package com.gdeguzman.springboot.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gdeguzman.springboot.entity.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {

	public List<Customer> findAll();
}
