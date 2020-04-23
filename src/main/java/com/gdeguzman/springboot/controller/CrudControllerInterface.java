package com.gdeguzman.springboot.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface CrudControllerInterface<T> {

	public List<T> findAll();
	public ResponseEntity<String> findById(@PathVariable int id);
	public ResponseEntity<String> findById(@RequestBody T entity);
	public ResponseEntity<String> Add(@RequestBody T entity);
	public ResponseEntity<String> Update(@RequestBody T entity);
	public ResponseEntity<String> Delete(@RequestBody T entity);
}
