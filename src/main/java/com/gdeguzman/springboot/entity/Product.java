package com.gdeguzman.springboot.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "product")
@AllArgsConstructor @Getter @Setter @NoArgsConstructor
public class Product{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int productid;
	
	@Column(name = "productname")
	private String productname;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "price")
	private double price;
	
}
