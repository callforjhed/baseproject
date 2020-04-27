package com.gdeguzman.springboot.controller;

import java.util.List;

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
import com.gdeguzman.springboot.service.CustomerService;


@RestController
@RequestMapping("/customer")
public class CustomerController implements CrudControllerInterface<Customer>{

	@Autowired
	CustomerRepository repository;	
	
	@Autowired
	CustomerService service;
	
		
	@RequestMapping("/findall")
	public List<Customer>findAll()
	{			
		return service.findAll();		
	}
		
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	@GetMapping("/{id}")
	public ResponseEntity<String> findById(@PathVariable int id) {
		Customer obj = service.findById(id);
		if(obj != null) {
			return new ResponseEntity(obj,HttpStatus.OK);
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}	
	}
		
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/findbyid")
	public ResponseEntity<String> findById(@RequestBody Customer entity)
	{		
		Customer obj = service.findById(entity.getCustomerid());
		if(obj != null) {
			return new ResponseEntity(obj,HttpStatus.OK);
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}		
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<String> Add(@RequestBody Customer entity)
	{
		if(null != entity) {
			try {
				service.save(entity);
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
				if(service.existsById(entity.getCustomerid())) {
					service.save(entity);
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
				if(service.existsById(entity.getCustomerid())) {
				service.delete(entity);
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
