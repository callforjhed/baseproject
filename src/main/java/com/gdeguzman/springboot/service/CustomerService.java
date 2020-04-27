package com.gdeguzman.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gdeguzman.springboot.entity.Customer;
import com.gdeguzman.springboot.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	CustomerRepository repository;

	public List<Customer> findAll() {
		return repository.findAll();
	}

	public Customer findById(int id) {
		return repository.findById(id).get();
	}

	public void save(Customer entity) {
		repository.save(entity);
	}

	public void delete(Customer entity) {
		repository.delete(entity);
	}
	
	public boolean existsById(int id) {
		return repository.existsById(id);
	}

}
