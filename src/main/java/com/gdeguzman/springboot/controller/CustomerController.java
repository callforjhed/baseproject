package com.gdeguzman.springboot.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gdeguzman.springboot.entity.Customer;
import com.gdeguzman.springboot.repository.CustomerRepository;


@RestController
@RequestMapping("/customer")
public class CustomerController implements CrudControllerInterface<Customer>{

	@Autowired
	CustomerRepository repository;	
	
		
	@RequestMapping("/findall")
	public List<Customer>findAll()
	{			
		return repository.findAll();		
	}
		
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	@GetMapping("/{id}")
	public ResponseEntity<String> findById(@PathVariable int id) {
		Optional<Customer> obj = repository.findById(Integer.valueOf(id));
		if(obj.isPresent()) {
			return new ResponseEntity(obj.get(),HttpStatus.OK);
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}	
	}
		
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/findbyid")
	public ResponseEntity<String> findById(@RequestBody Customer entity)
	{		
		Optional<Customer> obj = repository.findById(Integer.valueOf(entity.getCustomerid()));
		if(obj.isPresent()) {
			return new ResponseEntity(obj.get(),HttpStatus.OK);
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}		
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<String> Add(@RequestBody Customer entity)
	{
		if(null != entity) {
			try {
				repository.save(entity);
				return ResponseEntity.status(HttpStatus.OK).build();
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseEntity<String> Update(@RequestBody Customer entity)
	{
		if(null != entity) {
			try {
				if(repository.existsById(entity.getCustomerid())) {
				repository.save(entity);
				return ResponseEntity.status(HttpStatus.OK).build();
				}else {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
				}
				
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ResponseEntity<String> Delete(@RequestBody Customer entity)
	{
		if(null != entity) {
			try {
				if(repository.existsById(entity.getCustomerid())) {
				repository.delete(entity);
				return ResponseEntity.status(HttpStatus.OK).build();
				}else {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
				}
				
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
}
